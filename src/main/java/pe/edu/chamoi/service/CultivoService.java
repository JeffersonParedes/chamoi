package pe.edu.chamoi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.chamoi.model.Cultivo;
import pe.edu.chamoi.model.enums.EstadoGenerico;
import pe.edu.chamoi.repository.CultivoRepository;

import java.util.List;

@Service
public class CultivoService {

    private final CultivoRepository cultivoRepository;

    public CultivoService(CultivoRepository cultivoRepository) {
        this.cultivoRepository = cultivoRepository;
    }

    @Transactional(readOnly = true)
    public List<Cultivo> listarActivos() {
        return cultivoRepository.findByEstadoOrderByNombreAsc(EstadoGenerico.ACTIVO);
    }

    @Transactional(readOnly = true)
    public Cultivo buscarPorId(Long id) {
        return cultivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cultivo no encontrado con ID: " + id));
    }
}

