package pe.edu.chamoi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.chamoi.model.Usuario;
import pe.edu.chamoi.model.enums.EstadoUsuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @EntityGraph(attributePaths = {"rol"})
    Optional<Usuario> findByCorreo(String correo);

    List<Usuario> findByRolNombre(String nombreRol);
    List<Usuario> findByEstado(EstadoUsuario estado);
    List<Usuario> findByRolNombreAndEstado(String nombreRol, EstadoUsuario estado);
    long countByRolNombre(String nombreRol);
    long countByEstado(EstadoUsuario estado);
    boolean existsByCorreo(String correo);

    // Paginación con filtros
    Page<Usuario> findByRolNombre(String rolNombre, Pageable pageable);
    Page<Usuario> findByEstado(EstadoUsuario estado, Pageable pageable);
    Page<Usuario> findByRolNombreAndEstado(String rolNombre, EstadoUsuario estado, Pageable pageable);

    // Búsqueda por nombre o correo con paginación
    Page<Usuario> findByNombresContainingIgnoreCaseOrApellidosContainingIgnoreCaseOrCorreoContainingIgnoreCase(
        String nombres, String apellidos, String correo, Pageable pageable);
}
