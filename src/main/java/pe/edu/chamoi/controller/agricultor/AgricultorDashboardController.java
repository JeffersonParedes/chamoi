package pe.edu.chamoi.controller.agricultor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.chamoi.model.Analisis;
import pe.edu.chamoi.repository.AnalisisRepository;
import pe.edu.chamoi.security.CustomUserDetails;
import pe.edu.chamoi.service.PerfilService;

import java.util.List;

@Controller
@RequestMapping("/agricultor")
@PreAuthorize("hasRole('AGRICULTOR')")
public class AgricultorDashboardController {

    private final PerfilService perfilService;
    private final AnalisisRepository analisisRepository;

    public AgricultorDashboardController(PerfilService perfilService, AnalisisRepository analisisRepository) {
        this.perfilService = perfilService;
        this.analisisRepository = analisisRepository;
    }

    @GetMapping("/inicio")
    public String inicio(Model model, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            Long usuarioId = userDetails.getUsuario().getId();
            String nombre = userDetails.getNombreCompleto();
            boolean perfilCompleto = perfilService.perfilEstaCompleto(usuarioId, "AGRICULTOR");
            long totalAnalisis = analisisRepository.countByUsuarioId(usuarioId);
            List<Analisis> ultimosAnalisis = analisisRepository.findTop3ByUsuarioIdOrderByFechaSolicitudDesc(usuarioId);

            model.addAttribute("usuarioNombre", nombre);
            model.addAttribute("usuarioRol", userDetails.getRolNombre());
            model.addAttribute("perfilCompleto", perfilCompleto);
            model.addAttribute("totalAnalisis", totalAnalisis);
            model.addAttribute("ultimosAnalisis", ultimosAnalisis);
        }

        return "agricultor/inicio";
    }
}
