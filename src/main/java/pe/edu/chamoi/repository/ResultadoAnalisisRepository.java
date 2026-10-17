package pe.edu.chamoi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.chamoi.model.ResultadoAnalisis;

import java.util.Optional;

@Repository
public interface ResultadoAnalisisRepository extends JpaRepository<ResultadoAnalisis, Long> {
    Optional<ResultadoAnalisis> findByAnalisisId(Long analisisId);
}
