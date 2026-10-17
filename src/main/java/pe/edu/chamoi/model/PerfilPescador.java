package pe.edu.chamoi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "perfiles_pescadores")
public class PerfilPescador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    @Column(name = "departamento", length = 100)
    private String departamento;

    @Column(name = "provincia", length = 100)
    private String provincia;

    @Column(name = "zona_actividad", length = 255)
    private String zonaActividad;

    @Column(name = "tipo_pesca", length = 100)
    private String tipoPesca;

    @Column(name = "embarcacion", length = 100)
    private String embarcacion;

    @Column(name = "situacion_actual", columnDefinition = "text")
    private String situacionActual;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    public PerfilPescador() {
    }

    public PerfilPescador(Long id, Usuario usuario, String departamento, String provincia, 
                          String zonaActividad, String tipoPesca, String embarcacion, 
                          String situacionActual, LocalDateTime fechaActualizacion) {
        this.id = id;
        this.usuario = usuario;
        this.departamento = departamento;
        this.provincia = provincia;
        this.zonaActividad = zonaActividad;
        this.tipoPesca = tipoPesca;
        this.embarcacion = embarcacion;
        this.situacionActual = situacionActual;
        this.fechaActualizacion = fechaActualizacion;
    }

    public static PerfilPescadorBuilder builder() {
        return new PerfilPescadorBuilder();
    }

    public static class PerfilPescadorBuilder {
        private Long id;
        private Usuario usuario;
        private String departamento;
        private String provincia;
        private String zonaActividad;
        private String tipoPesca;
        private String embarcacion;
        private String situacionActual;
        private LocalDateTime fechaActualizacion;

        public PerfilPescadorBuilder id(Long id) { this.id = id; return this; }
        public PerfilPescadorBuilder usuario(Usuario usuario) { this.usuario = usuario; return this; }
        public PerfilPescadorBuilder departamento(String departamento) { this.departamento = departamento; return this; }
        public PerfilPescadorBuilder provincia(String provincia) { this.provincia = provincia; return this; }
        public PerfilPescadorBuilder zonaActividad(String zonaActividad) { this.zonaActividad = zonaActividad; return this; }
        public PerfilPescadorBuilder tipoPesca(String tipoPesca) { this.tipoPesca = tipoPesca; return this; }
        public PerfilPescadorBuilder embarcacion(String embarcacion) { this.embarcacion = embarcacion; return this; }
        public PerfilPescadorBuilder situacionActual(String situacionActual) { this.situacionActual = situacionActual; return this; }
        public PerfilPescadorBuilder fechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; return this; }

        public PerfilPescador build() {
            return new PerfilPescador(id, usuario, departamento, provincia, zonaActividad, 
                                      tipoPesca, embarcacion, situacionActual, fechaActualizacion);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }

    public String getZonaActividad() { return zonaActividad; }
    public void setZonaActividad(String zonaActividad) { this.zonaActividad = zonaActividad; }

    public String getTipoPesca() { return tipoPesca; }
    public void setTipoPesca(String tipoPesca) { this.tipoPesca = tipoPesca; }

    public String getEmbarcacion() { return embarcacion; }
    public void setEmbarcacion(String embarcacion) { this.embarcacion = embarcacion; }

    public String getSituacionActual() { return situacionActual; }
    public void setSituacionActual(String situacionActual) { this.situacionActual = situacionActual; }

    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerfilPescador that = (PerfilPescador) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PerfilPescador{" +
                "id=" + id +
                ", departamento='" + departamento + '\'' +
                ", provincia='" + provincia + '\'' +
                ", zonaActividad='" + zonaActividad + '\'' +
                ", tipoPesca='" + tipoPesca + '\'' +
                ", embarcacion='" + embarcacion + '\'' +
                '}';
    }
}
