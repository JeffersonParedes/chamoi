package pe.edu.chamoi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.chamoi.model.Especie;
import pe.edu.chamoi.model.enums.EstadoGenerico;
import pe.edu.chamoi.repository.EspecieRepository;

import java.util.List;

@Service
public class EspecieService {

    private final EspecieRepository especieRepository;

    public EspecieService(EspecieRepository especieRepository) {
        this.especieRepository = especieRepository;
    }

    @Transactional(readOnly = true)
    public List<Especie> listarActivas() {
        return especieRepository.findByEstadoOrderByNombreAsc(EstadoGenerico.ACTIVO);
    }

    @Transactional(readOnly = true)
    public Especie buscarPorId(Long id) {
        return especieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especie no encontrada con ID: " + id));
    }
}

