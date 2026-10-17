package pe.edu.chamoi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.chamoi.model.DatoFuente;
import pe.edu.chamoi.model.enums.TipoActividad;

import java.util.List;

@Repository
public interface DatoFuenteRepository extends JpaRepository<DatoFuente, Long> {
    List<DatoFuente> findByUbicacionNormalizadaStartingWithAndTipoActividad(String ubicacionNormalizada, TipoActividad tipoActividad);
}
