package pe.edu.chamoi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.chamoi.model.PerfilPescador;

import java.util.Optional;

@Repository
public interface PerfilPescadorRepository extends JpaRepository<PerfilPescador, Long> {
    Optional<PerfilPescador> findByUsuarioId(Long usuarioId);
}
