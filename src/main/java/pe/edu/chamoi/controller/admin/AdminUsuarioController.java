package pe.edu.chamoi.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.chamoi.model.Usuario;
import pe.edu.chamoi.repository.PerfilAgricultorRepository;
import pe.edu.chamoi.repository.PerfilPescadorRepository;
import pe.edu.chamoi.repository.UsuarioRepository;
import pe.edu.chamoi.security.CustomUserDetails;
import pe.edu.chamoi.service.AuditoriaService;
import pe.edu.chamoi.service.UsuarioService;

@Controller
@RequestMapping("/admin/usuarios")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepo;
    private final PerfilAgricultorRepository perfilAgricultorRepo;
    private final PerfilPescadorRepository perfilPescadorRepo;
    private final AuditoriaService auditoriaService;

    public AdminUsuarioController(UsuarioService usuarioService,
                                  UsuarioRepository usuarioRepo,
                                  PerfilAgricultorRepository perfilAgricultorRepo,
                                  PerfilPescadorRepository perfilPescadorRepo,
                                  AuditoriaService auditoriaService) {
        this.usuarioService = usuarioService;
        this.usuarioRepo = usuarioRepo;
        this.perfilAgricultorRepo = perfilAgricultorRepo;
        this.perfilPescadorRepo = perfilPescadorRepo;
        this.auditoriaService = auditoriaService;
    }

    @GetMapping
    public String listar(
            @RequestParam(required = false) String rol,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String busqueda,
            @RequestParam(defaultValue = "0") int pagina,
            Model model,
            Authentication auth,
            HttpServletRequest request) {
        
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            model.addAttribute("usuarioNombre", userDetails.getNombreCompleto());
            model.addAttribute("usuarioRol", userDetails.getRolNombre());
        }

        Page<Usuario> paginaUsuarios = usuarioService.listarConFiltrosYPaginacion(
            rol, estado, busqueda, pagina, 10);
        
        model.addAttribute("pagina", paginaUsuarios);
        model.addAttribute("usuarios", paginaUsuarios.getContent());
        model.addAttribute("rolFiltro", rol != null ? rol : "");
        model.addAttribute("estadoFiltro", estado != null ? estado : "");
        model.addAttribute("busquedaFiltro", busqueda != null ? busqueda : "");
        model.addAttribute("stats", usuarioService.contarPorRolYEstado());
        
        // Registrar consulta en auditoría
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isBlank() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        } else if (ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        
        Usuario admin = usuarioRepo.findByCorreo(auth.getName()).orElse(null);
        if (admin != null) {
            auditoriaService.registrar(
                admin.getId(),
                "CONSULTAR_USUARIOS",
                "Usuario",
                null,
                "Admin consultó la lista de usuarios (filtros: rol=" + rol + ", estado=" + estado + ", busqueda=" + busqueda + ")",
                ip
            );
        }
        
        return "admin/usuarios";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model, Authentication auth, HttpServletRequest request) {
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            model.addAttribute("usuarioNombre", userDetails.getNombreCompleto());
            model.addAttribute("usuarioRol", userDetails.getRolNombre());
        }

        Usuario usuario = usuarioService.buscarPorId(id);
        model.addAttribute("usuario", usuario);
        
        // Si es agricultor, traer su perfil
        if (usuario.getRol() != null && "AGRICULTOR".equalsIgnoreCase(usuario.getRol().getNombre())) {
            perfilAgricultorRepo.findByUsuarioId(id).ifPresent(p -> 
                model.addAttribute("perfilAgricultor", p));
        }
        // Si es pescador, traer su perfil
        if (usuario.getRol() != null && "PESCADOR".equalsIgnoreCase(usuario.getRol().getNombre())) {
            perfilPescadorRepo.findByUsuarioId(id).ifPresent(p -> 
                model.addAttribute("perfilPescador", p));
        }
        
        // Auditoría
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isBlank() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        } else if (ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }

        Usuario admin = usuarioRepo.findByCorreo(auth.getName()).orElse(null);
        if (admin != null) {
            auditoriaService.registrar(
                admin.getId(),
                "VER_DETALLE_USUARIO",
                "Usuario",
                id,
                "Admin consultó el detalle del usuario " + usuario.getCorreo(),
                ip
            );
        }
        
        return "admin/usuario-detalle";
    }
}
