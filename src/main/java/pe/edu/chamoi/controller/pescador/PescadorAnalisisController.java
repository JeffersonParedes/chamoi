package pe.edu.chamoi.controller.pescador;

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
import pe.edu.chamoi.service.EspecieService;

@Controller
@RequestMapping("/pescador/analisis")
@PreAuthorize("hasRole('PESCADOR')")
public class PescadorAnalisisController {

    private final AnalisisService analisisService;
    private final EspecieService especieService;
    private final UsuarioRepository usuarioRepo;

    public PescadorAnalisisController(AnalisisService analisisService,
                                      EspecieService especieService,
                                      UsuarioRepository usuarioRepo) {
        this.analisisService = analisisService;
        this.especieService = especieService;
        this.usuarioRepo = usuarioRepo;
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model, Authentication auth) {
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            model.addAttribute("usuarioNombre", userDetails.getNombreCompleto());
            model.addAttribute("usuarioRol", userDetails.getRolNombre());
        }

        model.addAttribute("analisisRequest", new AnalisisRequest());
        model.addAttribute("especies", especieService.listarActivas());
        return "pescador/analisis/nuevo";
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
            result.rejectValue("pregunta", "error.pregunta", "Debes escribir tu consulta o pregunta para el análisis pesquero");
        }

        if (result.hasErrors()) {
            model.addAttribute("especies", especieService.listarActivas());
            return "pescador/analisis/nuevo";
        }
        
        Usuario u = usuarioRepo.findByCorreo(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuario autenticado no encontrado"));
        
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isBlank() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        } else if (ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }

        Analisis a = analisisService.solicitarAnalisisPesquero(u.getId(), req, ip);
        
        redirectAttrs.addFlashAttribute("successMsg", "Análisis pesquero generado correctamente con el motor de reglas.");
        return "redirect:/pescador/analisis/" + a.getId();
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
            return "redirect:/pescador/historial";
        }
        
        ResultadoAnalisis resultado = analisisService.buscarResultadoPorAnalisis(id);

        model.addAttribute("analisis", a);
        model.addAttribute("resultado", resultado);
        if (resultado != null) {
            model.addAttribute("recomendaciones", analisisService.listarRecomendaciones(resultado.getId()));
        }
        return "pescador/analisis/resultado";
    }
}

