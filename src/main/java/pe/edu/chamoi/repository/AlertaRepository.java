package pe.edu.chamoi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.chamoi.model.Alerta;

import java.util.List;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    List<Alerta> findByUsuarioIdAndLeidaFalseOrderByFechaEventoDesc(Long usuarioId);
}
