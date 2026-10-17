package pe.edu.chamoi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.chamoi.model.SituacionPesquera;

import java.util.List;

@Repository
public interface SituacionPesqueraRepository extends JpaRepository<SituacionPesquera, Long> {
    List<SituacionPesquera> findByUsuarioId(Long usuarioId);
}
