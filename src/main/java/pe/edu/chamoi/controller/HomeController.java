package pe.edu.chamoi.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.chamoi.security.CustomUserDetails;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Authentication auth) {
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            return "redirect:/login";
        }

        boolean isAnonymous = auth.getAuthorities().stream()
            .anyMatch(a -> "ROLE_ANONYMOUS".equals(a.getAuthority()));
        if (isAnonymous) {
            return "redirect:/login";
        }
        
        String rol = auth.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .findFirst()
            .orElse("");

        return switch (rol) {
            case "ROLE_ADMIN"      -> "redirect:/admin/inicio";
            case "ROLE_AGRICULTOR" -> "redirect:/agricultor/inicio";
            case "ROLE_PESCADOR"   -> "redirect:/pescador/inicio";
            default                -> "redirect:/auth/exito";
        };
    }

    @GetMapping("/auth/exito")
    public String exito(Model model, Authentication auth) {
        String nombre = "Usuario";
        String rol = "N/A";
        if (auth != null && auth.isAuthenticated()) {
            if (auth.getPrincipal() instanceof CustomUserDetails userDetails) {
                nombre = userDetails.getNombreCompleto();
                rol = userDetails.getRolNombre();
            } else {
                nombre = auth.getName();
                rol = auth.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("N/A");
            }
        }
        model.addAttribute("nombre", nombre);
        model.addAttribute("rol", rol);
        return "auth/exito";
    }

    @GetMapping("/error")
    public String error(@RequestParam(required = false) String acceso, Model model) {
        if ("denegado".equals(acceso)) {
            model.addAttribute("msg", "No tienes permisos para acceder a esta sección.");
        }
        return "auth/error";
    }
}
