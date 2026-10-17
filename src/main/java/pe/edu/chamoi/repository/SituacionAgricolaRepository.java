package pe.edu.chamoi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.chamoi.model.SituacionAgricola;

import java.util.List;

@Repository
public interface SituacionAgricolaRepository extends JpaRepository<SituacionAgricola, Long> {
    List<SituacionAgricola> findByUsuarioId(Long usuarioId);
}
