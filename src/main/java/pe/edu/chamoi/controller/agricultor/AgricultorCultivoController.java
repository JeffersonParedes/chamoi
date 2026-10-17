package pe.edu.chamoi.controller.agricultor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.chamoi.model.Cultivo;
import pe.edu.chamoi.security.CustomUserDetails;
import pe.edu.chamoi.service.CultivoService;

import java.util.List;

@Controller
@RequestMapping("/agricultor/cultivos")
@PreAuthorize("hasRole('AGRICULTOR')")
public class AgricultorCultivoController {

    private final CultivoService cultivoService;

    public AgricultorCultivoController(CultivoService cultivoService) {
        this.cultivoService = cultivoService;
    }

    @GetMapping
    public String listarCultivos(Model model, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            model.addAttribute("usuarioNombre", userDetails.getNombreCompleto());
            model.addAttribute("usuarioRol", userDetails.getRolNombre());
        }

        List<Cultivo> cultivos = cultivoService.listarActivos();
        model.addAttribute("cultivos", cultivos);

        return "agricultor/cultivos";
    }

    @GetMapping("/{id}")
    public String verDetalleCultivo(@PathVariable("id") Long id,
                                    Model model,
                                    Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            model.addAttribute("usuarioNombre", userDetails.getNombreCompleto());
            model.addAttribute("usuarioRol", userDetails.getRolNombre());
        }

        Cultivo cultivo = cultivoService.buscarPorId(id);
        model.addAttribute("cultivo", cultivo);

        return "agricultor/cultivo-detalle";
    }
}

