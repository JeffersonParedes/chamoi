package pe.edu.chamoi.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.chamoi.dto.request.RegistroRequest;
import pe.edu.chamoi.service.AuthService;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String mostrarLogin(Model model,
                               @RequestParam(required = false) String error,
                               @RequestParam(required = false) String logout) {
        if (error != null) {
            model.addAttribute("errorMsg", "Correo o contraseña incorrectos");
        }
        if (logout != null) {
            model.addAttribute("logoutMsg", "Has cerrado sesión correctamente");
        }
        return "auth/login";
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("registroRequest", new RegistroRequest());
        return "auth/registro";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@Valid @ModelAttribute("registroRequest") RegistroRequest req,
                                   BindingResult result,
                                   RedirectAttributes redirectAttrs,
                                   HttpServletRequest request) {
        // Validación de campos del formulario
        if (result.hasErrors()) {
            return "auth/registro";
        }

        try {
            String ip = request.getHeader("X-Forwarded-For");
            if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            } else if (ip.contains(",")) {
                ip = ip.split(",")[0].trim();
            }
            authService.registrar(req, ip);
            redirectAttrs.addFlashAttribute("successMsg", 
                "✅ Registro exitoso. Ya puedes iniciar sesión.");
            return "redirect:/login";
        } catch (RuntimeException e) {
            String msg = e.getMessage() != null ? e.getMessage() : "Error durante el registro";
            if (msg.toLowerCase().contains("rol")) {
                result.rejectValue("rolNombre", "error.rol", msg);
            } else {
                result.rejectValue("correo", "error.correo", msg);
            }
            return "auth/registro";
        }
    }
}

