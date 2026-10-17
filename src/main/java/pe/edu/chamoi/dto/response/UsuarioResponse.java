package pe.edu.chamoi.dto.response;

import pe.edu.chamoi.model.enums.EstadoUsuario;
import java.time.LocalDateTime;

public class UsuarioResponse {

    private Long id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
    private String rolNombre;
    private EstadoUsuario estado;
    private LocalDateTime fechaRegistro;

    public UsuarioResponse() {
    }

    public UsuarioResponse(Long id, String nombres, String apellidos, String correo, 
                           String telefono, String rolNombre, EstadoUsuario estado, 
                           LocalDateTime fechaRegistro) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.rolNombre = rolNombre;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public static UsuarioResponseBuilder builder() {
        return new UsuarioResponseBuilder();
    }

    public static class UsuarioResponseBuilder {
        private Long id;
        private String nombres;
        private String apellidos;
        private String correo;
        private String telefono;
        private String rolNombre;
        private EstadoUsuario estado;
        private LocalDateTime fechaRegistro;

        public UsuarioResponseBuilder id(Long id) { this.id = id; return this; }
        public UsuarioResponseBuilder nombres(String nombres) { this.nombres = nombres; return this; }
        public UsuarioResponseBuilder apellidos(String apellidos) { this.apellidos = apellidos; return this; }
        public UsuarioResponseBuilder correo(String correo) { this.correo = correo; return this; }
        public UsuarioResponseBuilder telefono(String telefono) { this.telefono = telefono; return this; }
        public UsuarioResponseBuilder rolNombre(String rolNombre) { this.rolNombre = rolNombre; return this; }
        public UsuarioResponseBuilder estado(EstadoUsuario estado) { this.estado = estado; return this; }
        public UsuarioResponseBuilder fechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; return this; }

        public UsuarioResponse build() {
            return new UsuarioResponse(id, nombres, apellidos, correo, telefono, rolNombre, estado, fechaRegistro);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getRolNombre() { return rolNombre; }
    public void setRolNombre(String rolNombre) { this.rolNombre = rolNombre; }

    public EstadoUsuario getEstado() { return estado; }
    public void setEstado(EstadoUsuario estado) { this.estado = estado; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}
