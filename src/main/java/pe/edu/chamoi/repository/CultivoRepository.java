package pe.edu.chamoi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.chamoi.model.Cultivo;
import pe.edu.chamoi.model.enums.EstadoGenerico;

import java.util.List;

@Repository
public interface CultivoRepository extends JpaRepository<Cultivo, Long> {
    List<Cultivo> findByEstadoOrderByNombreAsc(EstadoGenerico estado);
}
