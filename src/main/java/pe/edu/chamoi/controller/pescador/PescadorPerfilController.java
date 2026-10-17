package pe.edu.chamoi.controller.pescador;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.chamoi.dto.request.PerfilPescadorRequest;
import pe.edu.chamoi.model.PerfilPescador;
import pe.edu.chamoi.security.CustomUserDetails;
import pe.edu.chamoi.service.PerfilService;

@Controller
@RequestMapping("/pescador/perfil")
@PreAuthorize("hasRole('PESCADOR')")
public class PescadorPerfilController {

    private final PerfilService perfilService;

    public PescadorPerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping
    public String verFormularioPerfil(Model model, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            Long usuarioId = userDetails.getUsuario().getId();
            model.addAttribute("usuarioNombre", userDetails.getNombreCompleto());
            model.addAttribute("usuarioRol", userDetails.getRolNombre());

            PerfilPescador perfil = perfilService.obtenerOCrearPerfilPescador(usuarioId);
            PerfilPescadorRequest req = new PerfilPescadorRequest(
                    perfil.getDepartamento(),
                    perfil.getProvincia(),
                    perfil.getZonaActividad(),
                    perfil.getTipoPesca(),
                    perfil.getEmbarcacion(),
                    perfil.getSituacionActual()
            );
            model.addAttribute("perfilRequest", req);
        }
        return "pescador/perfil";
    }

    @PostMapping
    public String guardarPerfil(@Valid @ModelAttribute("perfilRequest") PerfilPescadorRequest perfilRequest,
                                BindingResult bindingResult,
                                HttpServletRequest request,
                                RedirectAttributes redirectAttributes,
                                Authentication authentication,
                                Model model) {
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            model.addAttribute("usuarioNombre", userDetails.getNombreCompleto());
            model.addAttribute("usuarioRol", userDetails.getRolNombre());

            if (bindingResult.hasErrors()) {
                return "pescador/perfil";
            }

            Long usuarioId = userDetails.getUsuario().getId();
            String ip = request.getHeader("X-Forwarded-For");
            if (ip == null || ip.isBlank() || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }

            perfilService.guardarPerfilPescador(usuarioId, perfilRequest, ip);
            redirectAttributes.addFlashAttribute("mensajeExito", "Perfil de pescador actualizado con éxito.");
        }
        return "redirect:/pescador/perfil";
    }
}

