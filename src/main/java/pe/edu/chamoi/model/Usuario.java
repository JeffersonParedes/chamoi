package pe.edu.chamoi.model;

import jakarta.persistence.*;
import pe.edu.chamoi.model.enums.EstadoUsuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "correo", nullable = false, unique = true, length = 150)
    private String correo;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoUsuario estado = EstadoUsuario.ACTIVO;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private PerfilAgricultor perfilAgricultor;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private PerfilPescador perfilPescador;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Analisis> analisis = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MensajeChat> mensajesChat = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alerta> alertas = new ArrayList<>();

    @OneToMany(mappedBy = "usuarioCarga")
    private List<DatoFuente> datosFuente = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Auditoria> auditorias = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(Long id, Rol rol, String nombres, String apellidos, String correo, 
                   String passwordHash, String telefono, EstadoUsuario estado, 
                   LocalDateTime fechaRegistro, LocalDateTime fechaActualizacion) {
        this.id = id;
        this.rol = rol;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.passwordHash = passwordHash;
        this.telefono = telefono;
        this.estado = estado != null ? estado : EstadoUsuario.ACTIVO;
        this.fechaRegistro = fechaRegistro;
        this.fechaActualizacion = fechaActualizacion;
    }

    public static UsuarioBuilder builder() {
        return new UsuarioBuilder();
    }

    public static class UsuarioBuilder {
        private Long id;
        private Rol rol;
        private String nombres;
        private String apellidos;
        private String correo;
        private String passwordHash;
        private String telefono;
        private EstadoUsuario estado = EstadoUsuario.ACTIVO;
        private LocalDateTime fechaRegistro;
        private LocalDateTime fechaActualizacion;

        public UsuarioBuilder id(Long id) { this.id = id; return this; }
        public UsuarioBuilder rol(Rol rol) { this.rol = rol; return this; }
        public UsuarioBuilder nombres(String nombres) { this.nombres = nombres; return this; }
        public UsuarioBuilder apellidos(String apellidos) { this.apellidos = apellidos; return this; }
        public UsuarioBuilder correo(String correo) { this.correo = correo; return this; }
        public UsuarioBuilder passwordHash(String passwordHash) { this.passwordHash = passwordHash; return this; }
        public UsuarioBuilder telefono(String telefono) { this.telefono = telefono; return this; }
        public UsuarioBuilder estado(EstadoUsuario estado) { this.estado = estado; return this; }
        public UsuarioBuilder fechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; return this; }
        public UsuarioBuilder fechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; return this; }

        public Usuario build() {
            return new Usuario(id, rol, nombres, apellidos, correo, passwordHash, telefono, estado, fechaRegistro, fechaActualizacion);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public EstadoUsuario getEstado() { return estado; }
    public void setEstado(EstadoUsuario estado) { this.estado = estado; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }

    public PerfilAgricultor getPerfilAgricultor() { return perfilAgricultor; }
    public void setPerfilAgricultor(PerfilAgricultor perfilAgricultor) { this.perfilAgricultor = perfilAgricultor; }

    public PerfilPescador getPerfilPescador() { return perfilPescador; }
    public void setPerfilPescador(PerfilPescador perfilPescador) { this.perfilPescador = perfilPescador; }

    public List<Analisis> getAnalisis() { return analisis; }
    public void setAnalisis(List<Analisis> analisis) { this.analisis = analisis; }

    public List<MensajeChat> getMensajesChat() { return mensajesChat; }
    public void setMensajesChat(List<MensajeChat> mensajesChat) { this.mensajesChat = mensajesChat; }

    public List<Alerta> getAlertas() { return alertas; }
    public void setAlertas(List<Alerta> alertas) { this.alertas = alertas; }

    public List<DatoFuente> getDatosFuente() { return datosFuente; }
    public void setDatosFuente(List<DatoFuente> datosFuente) { this.datosFuente = datosFuente; }

    public List<Auditoria> getAuditorias() { return auditorias; }
    public void setAuditorias(List<Auditoria> auditorias) { this.auditorias = auditorias; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", estado=" + estado +
                ", fechaRegistro=" + fechaRegistro +
                ", fechaActualizacion=" + fechaActualizacion +
                '}';
    }
}
