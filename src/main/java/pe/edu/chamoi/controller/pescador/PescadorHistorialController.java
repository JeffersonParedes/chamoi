package pe.edu.chamoi.controller.pescador;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.chamoi.model.Usuario;
import pe.edu.chamoi.repository.UsuarioRepository;
import pe.edu.chamoi.security.CustomUserDetails;
import pe.edu.chamoi.service.AnalisisService;

@Controller
@RequestMapping("/pescador/historial")
@PreAuthorize("hasRole('PESCADOR')")
public class PescadorHistorialController {

    private final AnalisisService analisisService;
    private final UsuarioRepository usuarioRepo;

    public PescadorHistorialController(AnalisisService analisisService, UsuarioRepository usuarioRepo) {
        this.analisisService = analisisService;
        this.usuarioRepo = usuarioRepo;
    }

    @GetMapping
    public String listar(Model model, Authentication auth) {
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            model.addAttribute("usuarioNombre", userDetails.getNombreCompleto());
            model.addAttribute("usuarioRol", userDetails.getRolNombre());
        }

        Usuario u = usuarioRepo.findByCorreo(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuario autenticado no encontrado"));
        
        model.addAttribute("analisis", analisisService.listarPorUsuario(u.getId()));
        return "pescador/historial";
    }
}

