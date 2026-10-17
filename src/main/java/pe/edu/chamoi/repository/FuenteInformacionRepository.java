package pe.edu.chamoi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.chamoi.model.FuenteInformacion;
import pe.edu.chamoi.model.enums.EstadoFuente;

import java.util.List;

@Repository
public interface FuenteInformacionRepository extends JpaRepository<FuenteInformacion, Long> {
    List<FuenteInformacion> findByEstado(EstadoFuente estado);
}
