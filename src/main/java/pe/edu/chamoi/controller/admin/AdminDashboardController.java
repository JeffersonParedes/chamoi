package pe.edu.chamoi.controller.admin;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.chamoi.model.Auditoria;
import pe.edu.chamoi.model.enums.EstadoUsuario;
import pe.edu.chamoi.repository.AuditoriaRepository;
import pe.edu.chamoi.repository.UsuarioRepository;
import pe.edu.chamoi.security.CustomUserDetails;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminDashboardController {

    private final UsuarioRepository usuarioRepository;
    private final AuditoriaRepository auditoriaRepository;

    public AdminDashboardController(UsuarioRepository usuarioRepository, AuditoriaRepository auditoriaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.auditoriaRepository = auditoriaRepository;
    }

    @GetMapping("/inicio")
    public String inicio(Model model, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            model.addAttribute("usuarioNombre", userDetails.getNombreCompleto());
            model.addAttribute("usuarioRol", userDetails.getRolNombre());
        }

        long totalUsuarios = usuarioRepository.count();
        long totalAgricultores = usuarioRepository.countByRolNombre("AGRICULTOR");
        long totalPescadores = usuarioRepository.countByRolNombre("PESCADOR");
        long totalActivos = usuarioRepository.countByEstado(EstadoUsuario.ACTIVO);

        model.addAttribute("totalUsuarios", totalUsuarios);
        model.addAttribute("totalAgricultores", totalAgricultores);
        model.addAttribute("totalPescadores", totalPescadores);
        model.addAttribute("totalActivos", totalActivos);

        Pageable top5 = PageRequest.of(0, 5, Sort.by("fechaEvento").descending());
        List<Auditoria> ultimasAuditorias = auditoriaRepository.findAll(top5).getContent();
        model.addAttribute("ultimasAuditorias", ultimasAuditorias);

        return "admin/inicio";
    }
}
