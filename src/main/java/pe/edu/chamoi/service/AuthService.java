package pe.edu.chamoi.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.chamoi.dto.request.RegistroRequest;
import pe.edu.chamoi.model.Rol;
import pe.edu.chamoi.model.Usuario;
import pe.edu.chamoi.model.enums.EstadoUsuario;
import pe.edu.chamoi.repository.RolRepository;
import pe.edu.chamoi.repository.UsuarioRepository;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepo;
    private final RolRepository rolRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuditoriaService auditoriaService;

    public AuthService(UsuarioRepository usuarioRepo, RolRepository rolRepo,
                       PasswordEncoder passwordEncoder, AuditoriaService auditoriaService) {
        this.usuarioRepo = usuarioRepo;
        this.rolRepo = rolRepo;
        this.passwordEncoder = passwordEncoder;
        this.auditoriaService = auditoriaService;
    }

    @Transactional
    public Usuario registrar(RegistroRequest req, String ip) {
        // 1. Verificar que el correo no exista
        if (usuarioRepo.findByCorreo(req.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        // 2. Verificar que el rol sea válido (solo AGRICULTOR o PESCADOR)
        if (!"AGRICULTOR".equalsIgnoreCase(req.getRolNombre()) && 
            !"PESCADOR".equalsIgnoreCase(req.getRolNombre())) {
            throw new RuntimeException("Rol inválido. Solo se permite AGRICULTOR o PESCADOR");
        }

        String rolNombreNormalizado = req.getRolNombre().toUpperCase();

        // 3. Buscar el rol
        Rol rol = rolRepo.findByNombre(rolNombreNormalizado)
            .orElseThrow(() -> new RuntimeException("Rol no existe: " + rolNombreNormalizado));

        // 4. Crear usuario
        Usuario usuario = Usuario.builder()
            .rol(rol)
            .nombres(req.getNombres())
            .apellidos(req.getApellidos())
            .correo(req.getCorreo())
            .passwordHash(passwordEncoder.encode(req.getPassword()))
            .telefono(req.getTelefono())
            .estado(EstadoUsuario.ACTIVO)
            .fechaRegistro(LocalDateTime.now())
            .fechaActualizacion(LocalDateTime.now())
            .build();

        Usuario guardado = usuarioRepo.save(usuario);

        // 5. Registrar en auditoría
        auditoriaService.registrar(
            guardado.getId(),
            "REGISTRO",
            "Usuario",
            guardado.getId(),
            "Nuevo usuario registrado con rol " + rol.getNombre(),
            ip
        );

        return guardado;
    }
}

