package pe.edu.chamoi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.chamoi.model.Usuario;
import pe.edu.chamoi.model.enums.EstadoUsuario;
import pe.edu.chamoi.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepo;

    public UsuarioService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepo.findByCorreo(correo);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    public List<Usuario> listarTodos() {
        return usuarioRepo.findAll();
    }

    public List<Usuario> listarActivos() {
        return usuarioRepo.findByEstado(EstadoUsuario.ACTIVO);
    }

    @Transactional(readOnly = true)
    public Page<Usuario> listarConFiltrosYPaginacion(
            String rol, String estado, String busqueda, int pagina, int tamaño) {
        
        Pageable pageable = PageRequest.of(pagina, tamaño, Sort.by("fechaRegistro").descending());
        
        // Si hay búsqueda por nombre/correo
        if (busqueda != null && !busqueda.trim().isEmpty()) {
            String termino = busqueda.trim();
            return usuarioRepo.findByNombresContainingIgnoreCaseOrApellidosContainingIgnoreCaseOrCorreoContainingIgnoreCase(
                termino, termino, termino, pageable);
        }
        
        // Filtros por rol/estado
        boolean hasRol = rol != null && !rol.trim().isEmpty();
        boolean hasEstado = estado != null && !estado.trim().isEmpty();

        EstadoUsuario estadoEnum = null;
        if (hasEstado) {
            try {
                estadoEnum = EstadoUsuario.valueOf(estado.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                hasEstado = false;
            }
        }

        if (hasRol && hasEstado) {
            return usuarioRepo.findByRolNombreAndEstado(rol.trim().toUpperCase(), estadoEnum, pageable);
        }
        if (hasRol) {
            return usuarioRepo.findByRolNombre(rol.trim().toUpperCase(), pageable);
        }
        if (hasEstado) {
            return usuarioRepo.findByEstado(estadoEnum, pageable);
        }
        
        return usuarioRepo.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Map<String, Long> contarPorRolYEstado() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("total", usuarioRepo.count());
        stats.put("agricultores", (long) usuarioRepo.findByRolNombre("AGRICULTOR").size());
        stats.put("pescadores", (long) usuarioRepo.findByRolNombre("PESCADOR").size());
        stats.put("admins", (long) usuarioRepo.findByRolNombre("ADMIN").size());
        stats.put("activos", (long) usuarioRepo.findByEstado(EstadoUsuario.ACTIVO).size());
        stats.put("inactivos", (long) usuarioRepo.findByEstado(EstadoUsuario.INACTIVO).size());
        return stats;
    }

    @Transactional
    public Usuario cambiarEstado(Long id) {
        Usuario u = buscarPorId(id);
        u.setEstado(u.getEstado() == EstadoUsuario.ACTIVO 
            ? EstadoUsuario.INACTIVO 
            : EstadoUsuario.ACTIVO);
        u.setFechaActualizacion(LocalDateTime.now());
        return usuarioRepo.save(u);
    }
}
