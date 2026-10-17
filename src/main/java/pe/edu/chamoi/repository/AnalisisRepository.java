package pe.edu.chamoi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.chamoi.model.Analisis;
import pe.edu.chamoi.model.enums.EstadoAnalisis;

import java.util.List;

@Repository
public interface AnalisisRepository extends JpaRepository<Analisis, Long> {
    List<Analisis> findByUsuarioIdOrderByFechaSolicitudDesc(Long usuarioId);
    List<Analisis> findTop3ByUsuarioIdOrderByFechaSolicitudDesc(Long usuarioId);
    List<Analisis> findByEstado(EstadoAnalisis estado);
    long countByUsuarioId(Long usuarioId);
}
