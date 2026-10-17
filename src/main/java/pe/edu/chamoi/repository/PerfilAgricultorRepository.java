package pe.edu.chamoi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.chamoi.model.PerfilAgricultor;

import java.util.Optional;

@Repository
public interface PerfilAgricultorRepository extends JpaRepository<PerfilAgricultor, Long> {
    Optional<PerfilAgricultor> findByUsuarioId(Long usuarioId);
}
