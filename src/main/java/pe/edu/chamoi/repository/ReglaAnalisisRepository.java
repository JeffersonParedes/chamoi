package pe.edu.chamoi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.chamoi.model.ReglaAnalisis;
import pe.edu.chamoi.model.enums.EstadoRegla;
import pe.edu.chamoi.model.enums.TipoActividadAmplio;

import java.util.List;

@Repository
public interface ReglaAnalisisRepository extends JpaRepository<ReglaAnalisis, Long> {
    List<ReglaAnalisis> findByTipoActividadInAndEstado(List<TipoActividadAmplio> tiposActividad, EstadoRegla estado);
}
