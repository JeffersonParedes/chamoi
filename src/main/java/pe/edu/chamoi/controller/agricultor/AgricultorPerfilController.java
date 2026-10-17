package pe.edu.chamoi.controller.agricultor;

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
import pe.edu.chamoi.dto.request.PerfilAgricultorRequest;
import pe.edu.chamoi.model.PerfilAgricultor;
import pe.edu.chamoi.security.CustomUserDetails;
import pe.edu.chamoi.service.PerfilService;

@Controller
@RequestMapping("/agricultor/perfil")
@PreAuthorize("hasRole('AGRICULTOR')")
public class AgricultorPerfilController {

    private final PerfilService perfilService;

    public AgricultorPerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping
    public String verFormularioPerfil(Model model, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            Long usuarioId = userDetails.getUsuario().getId();
            model.addAttribute("usuarioNombre", userDetails.getNombreCompleto());
            model.addAttribute("usuarioRol", userDetails.getRolNombre());

            PerfilAgricultor perfil = perfilService.obtenerOCrearPerfilAgricultor(usuarioId);
            PerfilAgricultorRequest req = new PerfilAgricultorRequest(
                    perfil.getDepartamento(),
                    perfil.getProvincia(),
                    perfil.getDistrito(),
                    perfil.getUbicacionDetalle(),
                    perfil.getAreaTerreno(),
                    perfil.getUnidadArea() != null ? perfil.getUnidadArea() : "ha",
                    perfil.getTipoTerreno(),
                    perfil.getSistemaRiego(),
                    perfil.getObjetivo(),
                    perfil.getSituacionActual()
            );
            model.addAttribute("perfilRequest", req);
        }
        return "agricultor/perfil";
    }

    @PostMapping
    public String guardarPerfil(@Valid @ModelAttribute("perfilRequest") PerfilAgricultorRequest perfilRequest,
                                BindingResult bindingResult,
                                HttpServletRequest request,
                                RedirectAttributes redirectAttributes,
                                Authentication authentication,
                                Model model) {
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            model.addAttribute("usuarioNombre", userDetails.getNombreCompleto());
            model.addAttribute("usuarioRol", userDetails.getRolNombre());

            if (bindingResult.hasErrors()) {
                return "agricultor/perfil";
            }

            Long usuarioId = userDetails.getUsuario().getId();
            String ip = request.getHeader("X-Forwarded-For");
            if (ip == null || ip.isBlank() || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }

            perfilService.guardarPerfilAgricultor(usuarioId, perfilRequest, ip);
            redirectAttributes.addFlashAttribute("mensajeExito", "Perfil de agricultor actualizado con éxito.");
        }
        return "redirect:/agricultor/perfil";
    }
}

