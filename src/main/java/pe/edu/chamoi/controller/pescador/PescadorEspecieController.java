package pe.edu.chamoi.controller.pescador;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.chamoi.model.Especie;
import pe.edu.chamoi.security.CustomUserDetails;
import pe.edu.chamoi.service.EspecieService;

import java.util.List;

@Controller
@RequestMapping("/pescador/especies")
@PreAuthorize("hasRole('PESCADOR')")
public class PescadorEspecieController {

    private final EspecieService especieService;

    public PescadorEspecieController(EspecieService especieService) {
        this.especieService = especieService;
    }

    @GetMapping
    public String listarEspecies(Model model, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            model.addAttribute("usuarioNombre", userDetails.getNombreCompleto());
            model.addAttribute("usuarioRol", userDetails.getRolNombre());
        }

        List<Especie> especies = especieService.listarActivas();
        model.addAttribute("especies", especies);

        return "pescador/especies";
    }

    @GetMapping("/{id}")
    public String verDetalleEspecie(@PathVariable("id") Long id,
                                   Model model,
                                   Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            model.addAttribute("usuarioNombre", userDetails.getNombreCompleto());
            model.addAttribute("usuarioRol", userDetails.getRolNombre());
        }

        Especie especie = especieService.buscarPorId(id);
        model.addAttribute("especie", especie);

        return "pescador/especie-detalle";
    }
}

