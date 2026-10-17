package pe.edu.chamoi.controller.agricultor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.chamoi.dto.request.AnalisisRequest;
import pe.edu.chamoi.model.Analisis;
import pe.edu.chamoi.model.ResultadoAnalisis;
import pe.edu.chamoi.model.Usuario;
import pe.edu.chamoi.repository.UsuarioRepository;
import pe.edu.chamoi.security.CustomUserDetails;
import pe.edu.chamoi.service.AnalisisService;
import pe.edu.chamoi.service.CultivoService;

@Controller
@RequestMapping("/agricultor/analisis")
@PreAuthorize("hasRole('AGRICULTOR')")
public class AgricultorAnalisisController {

    private final AnalisisService analisisService;
    private final CultivoService cultivoService;
    private final UsuarioRepository usuarioRepo;

    public AgricultorAnalisisController(AnalisisService analisisService,
                                        CultivoService cultivoService,
                                        UsuarioRepository usuarioRepo) {
        this.analisisService = analisisService;
        this.cultivoService = cultivoService;
        this.usuarioRepo = usuarioRepo;
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model, Authentication auth) {
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            model.addAttribute("usuarioNombre", userDetails.getNombreCompleto());
            model.addAttribute("usuarioRol", userDetails.getRolNombre());
        }

        model.addAttribute("analisisRequest", new AnalisisRequest());
        model.addAttribute("cultivos", cultivoService.listarActivos());
        return "agricultor/analisis/nuevo";
    }

    @PostMapping
    public String crear(@Valid @ModelAttribute("analisisRequest") AnalisisRequest req,
                        BindingResult result,
                        Authentication auth,
                        HttpServletRequest request,
                        Model model,
                        RedirectAttributes redirectAttrs) {
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            model.addAttribute("usuarioNombre", userDetails.getNombreCompleto());
            model.addAttribute("usuarioRol", userDetails.getRolNombre());
        }

        if (req.getPregunta() == null || req.getPregunta().isBlank()) {
            result.rejectValue("pregunta", "error.pregunta", "Debes escribir tu consulta o pregunta para el análisis");
        }

        if (result.hasErrors()) {
            model.addAttribute("cultivos", cultivoService.listarActivos());
            return "agricultor/analisis/nuevo";
        }
        
        Usuario u = usuarioRepo.findByCorreo(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuario autenticado no encontrado"));
        
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isBlank() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        } else if (ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }

        Analisis a = analisisService.solicitarAnalisisAgricola(u.getId(), req, ip);
        
        redirectAttrs.addFlashAttribute("successMsg", "Análisis agronómico generado correctamente con el motor de reglas.");
        return "redirect:/agricultor/analisis/" + a.getId();
    }

    @GetMapping("/{id}")
    public String ver(@PathVariable Long id, Model model, Authentication auth) {
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            model.addAttribute("usuarioNombre", userDetails.getNombreCompleto());
            model.addAttribute("usuarioRol", userDetails.getRolNombre());
        }

        Analisis a = analisisService.buscarPorId(id);
        Usuario u = usuarioRepo.findByCorreo(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuario autenticado no encontrado"));
        
        // Validar que el análisis pertenezca al usuario
        if (!a.getUsuario().getId().equals(u.getId())) {
            return "redirect:/agricultor/historial";
        }
        
        ResultadoAnalisis resultado = analisisService.buscarResultadoPorAnalisis(id);

        model.addAttribute("analisis", a);
        model.addAttribute("resultado", resultado);
        if (resultado != null) {
            model.addAttribute("recomendaciones", analisisService.listarRecomendaciones(resultado.getId()));
        }
        return "agricultor/analisis/resultado";
    }
}

