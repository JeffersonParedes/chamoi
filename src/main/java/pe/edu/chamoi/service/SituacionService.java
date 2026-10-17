package pe.edu.chamoi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.chamoi.model.SituacionAgricola;
import pe.edu.chamoi.model.SituacionPesquera;
import pe.edu.chamoi.repository.SituacionAgricolaRepository;
import pe.edu.chamoi.repository.SituacionPesqueraRepository;

@Service
@Transactional(readOnly = true)
public class SituacionService {

    private final SituacionAgricolaRepository agrRepo;
    private final SituacionPesqueraRepository pesRepo;

    public SituacionService(SituacionAgricolaRepository agrRepo, SituacionPesqueraRepository pesRepo) {
        this.agrRepo = agrRepo;
        this.pesRepo = pesRepo;
    }

    public SituacionAgricola buscarAgricola(Long id) {
        return agrRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Situación agrícola no encontrada con ID: " + id));
    }

    public SituacionPesquera buscarPesquera(Long id) {
        return pesRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Situación pesquera no encontrada con ID: " + id));
    }
}

