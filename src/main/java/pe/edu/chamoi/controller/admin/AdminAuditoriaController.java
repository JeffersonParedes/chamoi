package pe.edu.chamoi.controller.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.chamoi.model.Auditoria;
import pe.edu.chamoi.repository.AuditoriaRepository;
import pe.edu.chamoi.security.CustomUserDetails;

@Controller
@RequestMapping("/admin/auditoria")
@PreAuthorize("hasRole('ADMIN')")
public class AdminAuditoriaController {

    private final AuditoriaRepository auditoriaRepo;

    public AdminAuditoriaController(AuditoriaRepository auditoriaRepo) {
        this.auditoriaRepo = auditoriaRepo;
    }

    @GetMapping
    public String listar(@RequestParam(defaultValue = "0") int pagina,
                         Model model,
                         Authentication auth) {
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            model.addAttribute("usuarioNombre", userDetails.getNombreCompleto());
            model.addAttribute("usuarioRol", userDetails.getRolNombre());
        }

        Pageable pageable = PageRequest.of(pagina, 20, Sort.by("fechaEvento").descending());
        Page<Auditoria> paginaAuditoria = auditoriaRepo.findAll(pageable);
        model.addAttribute("pagina", paginaAuditoria);
        model.addAttribute("registros", paginaAuditoria.getContent());
        return "admin/auditoria";
    }
}

