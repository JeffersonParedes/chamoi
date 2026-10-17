package pe.edu.chamoi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.chamoi.dto.request.PerfilAgricultorRequest;
import pe.edu.chamoi.dto.request.PerfilPescadorRequest;
import pe.edu.chamoi.model.PerfilAgricultor;
import pe.edu.chamoi.model.PerfilPescador;
import pe.edu.chamoi.model.Usuario;
import pe.edu.chamoi.repository.PerfilAgricultorRepository;
import pe.edu.chamoi.repository.PerfilPescadorRepository;
import pe.edu.chamoi.repository.UsuarioRepository;

import java.time.LocalDateTime;

@Service
public class PerfilService {

    private final PerfilAgricultorRepository perfilAgricultorRepo;
    private final PerfilPescadorRepository perfilPescadorRepo;
    private final UsuarioRepository usuarioRepo;
    private final AuditoriaService auditoriaService;

    public PerfilService(PerfilAgricultorRepository perfilAgricultorRepo,
                         PerfilPescadorRepository perfilPescadorRepo,
                         UsuarioRepository usuarioRepo,
                         AuditoriaService auditoriaService) {
        this.perfilAgricultorRepo = perfilAgricultorRepo;
        this.perfilPescadorRepo = perfilPescadorRepo;
        this.usuarioRepo = usuarioRepo;
        this.auditoriaService = auditoriaService;
    }

    @Transactional
    public PerfilAgricultor obtenerOCrearPerfilAgricultor(Long usuarioId) {
        return perfilAgricultorRepo.findByUsuarioId(usuarioId)
            .orElseGet(() -> {
                Usuario usuario = usuarioRepo.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));
                PerfilAgricultor perfil = new PerfilAgricultor();
                perfil.setUsuario(usuario);
                perfil.setFechaActualizacion(LocalDateTime.now());
                return perfilAgricultorRepo.save(perfil);
            });
    }

    @Transactional
    public PerfilPescador obtenerOCrearPerfilPescador(Long usuarioId) {
        return perfilPescadorRepo.findByUsuarioId(usuarioId)
            .orElseGet(() -> {
                Usuario usuario = usuarioRepo.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));
                PerfilPescador perfil = new PerfilPescador();
                perfil.setUsuario(usuario);
                perfil.setFechaActualizacion(LocalDateTime.now());
                return perfilPescadorRepo.save(perfil);
            });
    }

    @Transactional
    public PerfilAgricultor guardarPerfilAgricultor(Long usuarioId, PerfilAgricultorRequest req, String ip) {
        PerfilAgricultor perfil = obtenerOCrearPerfilAgricultor(usuarioId);
        perfil.setDepartamento(req.getDepartamento());
        perfil.setProvincia(req.getProvincia());
        perfil.setDistrito(req.getDistrito());
        perfil.setUbicacionDetalle(req.getUbicacionDetalle());
        perfil.setAreaTerreno(req.getAreaTerreno());
        perfil.setUnidadArea(req.getUnidadArea() != null && !req.getUnidadArea().isBlank() ? req.getUnidadArea() : "ha");
        perfil.setTipoTerreno(req.getTipoTerreno());
        perfil.setSistemaRiego(req.getSistemaRiego());
        perfil.setObjetivo(req.getObjetivo());
        perfil.setSituacionActual(req.getSituacionActual());
        perfil.setFechaActualizacion(LocalDateTime.now());

        PerfilAgricultor guardado = perfilAgricultorRepo.save(perfil);

        auditoriaService.registrar(
            usuarioId,
            "ACTUALIZAR_PERFIL",
            "PerfilAgricultor",
            guardado.getId(),
            "Perfil de agricultor actualizado en " + guardado.getDepartamento() + "/" + guardado.getProvincia(),
            ip
        );

        return guardado;
    }

    @Transactional
    public PerfilPescador guardarPerfilPescador(Long usuarioId, PerfilPescadorRequest req, String ip) {
        PerfilPescador perfil = obtenerOCrearPerfilPescador(usuarioId);
        perfil.setDepartamento(req.getDepartamento());
        perfil.setProvincia(req.getProvincia());
        perfil.setZonaActividad(req.getZonaActividad());
        perfil.setTipoPesca(req.getTipoPesca());
        perfil.setEmbarcacion(req.getEmbarcacion());
        perfil.setSituacionActual(req.getSituacionActual());
        perfil.setFechaActualizacion(LocalDateTime.now());

        PerfilPescador guardado = perfilPescadorRepo.save(perfil);

        auditoriaService.registrar(
            usuarioId,
            "ACTUALIZAR_PERFIL",
            "PerfilPescador",
            guardado.getId(),
            "Perfil de pescador actualizado en " + guardado.getDepartamento() + "/" + guardado.getProvincia(),
            ip
        );

        return guardado;
    }

    @Transactional(readOnly = true)
    public boolean perfilEstaCompleto(Long usuarioId, String rol) {
        if ("AGRICULTOR".equalsIgnoreCase(rol) || "ROLE_AGRICULTOR".equalsIgnoreCase(rol)) {
            return perfilAgricultorRepo.findByUsuarioId(usuarioId)
                .map(p -> p.getDepartamento() != null && !p.getDepartamento().isBlank()
                       && p.getProvincia() != null && !p.getProvincia().isBlank()
                       && p.getAreaTerreno() != null)
                .orElse(false);
        } else if ("PESCADOR".equalsIgnoreCase(rol) || "ROLE_PESCADOR".equalsIgnoreCase(rol)) {
            return perfilPescadorRepo.findByUsuarioId(usuarioId)
                .map(p -> p.getDepartamento() != null && !p.getDepartamento().isBlank()
                       && p.getProvincia() != null && !p.getProvincia().isBlank()
                       && p.getZonaActividad() != null && !p.getZonaActividad().isBlank())
                .orElse(false);
        }
        return true;
    }
}

