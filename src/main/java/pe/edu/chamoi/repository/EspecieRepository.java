package pe.edu.chamoi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.chamoi.model.Especie;
import pe.edu.chamoi.model.enums.EstadoGenerico;

import java.util.List;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Long> {
    List<Especie> findByEstadoOrderByNombreAsc(EstadoGenerico estado);
}
