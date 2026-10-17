package pe.edu.chamoi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "auditoria")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "accion", nullable = false, length = 100)
    private String accion;

    @Column(name = "entidad", nullable = false, length = 100)
    private String entidad;

    @Column(name = "entidad_id")
    private Long entidadId;

    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;

    @Column(name = "direccion_ip", length = 45)
    private String direccionIp;

    @Column(name = "fecha_evento")
    private LocalDateTime fechaEvento;

    public Auditoria() {
    }

    public Auditoria(Long id, Usuario usuario, String accion, String entidad, Long entidadId, 
                     String descripcion, String direccionIp, LocalDateTime fechaEvento) {
        this.id = id;
        this.usuario = usuario;
        this.accion = accion;
        this.entidad = entidad;
        this.entidadId = entidadId;
        this.descripcion = descripcion;
        this.direccionIp = direccionIp;
        this.fechaEvento = fechaEvento;
    }

    public static AuditoriaBuilder builder() {
        return new AuditoriaBuilder();
    }

    public static class AuditoriaBuilder {
        private Long id;
        private Usuario usuario;
        private String accion;
        private String entidad;
        private Long entidadId;
        private String descripcion;
        private String direccionIp;
        private LocalDateTime fechaEvento;

        public AuditoriaBuilder id(Long id) { this.id = id; return this; }
        public AuditoriaBuilder usuario(Usuario usuario) { this.usuario = usuario; return this; }
        public AuditoriaBuilder accion(String accion) { this.accion = accion; return this; }
        public AuditoriaBuilder entidad(String entidad) { this.entidad = entidad; return this; }
        public AuditoriaBuilder entidadId(Long entidadId) { this.entidadId = entidadId; return this; }
        public AuditoriaBuilder descripcion(String descripcion) { this.descripcion = descripcion; return this; }
        public AuditoriaBuilder direccionIp(String direccionIp) { this.direccionIp = direccionIp; return this; }
        public AuditoriaBuilder fechaEvento(LocalDateTime fechaEvento) { this.fechaEvento = fechaEvento; return this; }

        public Auditoria build() {
            return new Auditoria(id, usuario, accion, entidad, entidadId, descripcion, direccionIp, fechaEvento);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getAccion() { return accion; }
    public void setAccion(String accion) { this.accion = accion; }

    public String getEntidad() { return entidad; }
    public void setEntidad(String entidad) { this.entidad = entidad; }

    public Long getEntidadId() { return entidadId; }
    public void setEntidadId(Long entidadId) { this.entidadId = entidadId; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getDireccionIp() { return direccionIp; }
    public void setDireccionIp(String direccionIp) { this.direccionIp = direccionIp; }

    public LocalDateTime getFechaEvento() { return fechaEvento; }
    public void setFechaEvento(LocalDateTime fechaEvento) { this.fechaEvento = fechaEvento; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auditoria auditoria = (Auditoria) o;
        return Objects.equals(id, auditoria.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Auditoria{" +
                "id=" + id +
                ", accion='" + accion + '\'' +
                ", entidad='" + entidad + '\'' +
                ", entidadId=" + entidadId +
                ", fechaEvento=" + fechaEvento +
                '}';
    }
}
