package pe.edu.chamoi.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.chamoi.model.Rol;
import pe.edu.chamoi.model.Usuario;
import pe.edu.chamoi.model.enums.EstadoUsuario;
import pe.edu.chamoi.repository.RolRepository;
import pe.edu.chamoi.repository.UsuarioRepository;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepo;
    private final RolRepository rolRepo;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository usuarioRepo, RolRepository rolRepo, PasswordEncoder passwordEncoder) {
        this.usuarioRepo = usuarioRepo;
        this.rolRepo = rolRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        // Asegurar que existan los roles iniciales en el sistema
        Rol rolAdmin = rolRepo.findByNombre("ADMIN")
            .orElseGet(() -> rolRepo.save(Rol.builder()
                .nombre("ADMIN")
                .descripcion("Administrador general del sistema")
                .build()));

        if (rolRepo.findByNombre("AGRICULTOR").isEmpty()) {
            rolRepo.save(Rol.builder()
                .nombre("AGRICULTOR")
                .descripcion("Productor agrícola")
                .build());
        }

        if (rolRepo.findByNombre("PESCADOR").isEmpty()) {
            rolRepo.save(Rol.builder()
                .nombre("PESCADOR")
                .descripcion("Productor pesquero")
                .build());
        }

        // Si no existe el admin, créalo
        if (usuarioRepo.findByCorreo("admin@chamoi.pe").isEmpty()) {
            Usuario admin = Usuario.builder()
                .rol(rolAdmin)
                .nombres("Administrador")
                .apellidos("CHAMOI")
                .correo("admin@chamoi.pe")
                .passwordHash(passwordEncoder.encode("admin123"))
                .telefono("999999999")
                .estado(EstadoUsuario.ACTIVO)
                .fechaRegistro(LocalDateTime.now())
                .fechaActualizacion(LocalDateTime.now())
                .build();

            usuarioRepo.save(admin);
            System.out.println("✅ Admin creado: admin@chamoi.pe / admin123");
        }
    }
}

