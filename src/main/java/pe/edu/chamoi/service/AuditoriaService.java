package pe.edu.chamoi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.chamoi.model.Auditoria;
import pe.edu.chamoi.model.Usuario;
import pe.edu.chamoi.repository.AuditoriaRepository;
import pe.edu.chamoi.repository.UsuarioRepository;

import java.time.LocalDateTime;

@Service
public class AuditoriaService {

    private final AuditoriaRepository auditoriaRepo;
    private final UsuarioRepository usuarioRepo;

    public AuditoriaService(AuditoriaRepository auditoriaRepo, UsuarioRepository usuarioRepo) {
        this.auditoriaRepo = auditoriaRepo;
        this.usuarioRepo = usuarioRepo;
    }

    @Transactional
    public void registrar(Long usuarioId, String accion, String entidad, 
                          Long entidadId, String descripcion, String ip) {
        try {
            Usuario usuario = usuarioId != null 
                ? usuarioRepo.findById(usuarioId).orElse(null) 
                : null;

            Auditoria a = Auditoria.builder()
                .usuario(usuario)
                .accion(accion)
                .entidad(entidad)
                .entidadId(entidadId)
                .descripcion(descripcion)
                .direccionIp(ip)
                .fechaEvento(LocalDateTime.now())
                .build();

            auditoriaRepo.save(a);
        } catch (Exception e) {
            // Se previene que una falla de auditoría interrumpa el flujo de negocio principal
            System.err.println("Error al guardar auditoría: " + e.getMessage());
        }
    }
}

