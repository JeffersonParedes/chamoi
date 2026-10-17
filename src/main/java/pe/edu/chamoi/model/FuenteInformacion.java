package pe.edu.chamoi.model;

import jakarta.persistence.*;
import pe.edu.chamoi.model.enums.EstadoFuente;
import pe.edu.chamoi.model.enums.FormatoFuente;
import pe.edu.chamoi.model.enums.TipoActividadAmplio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "fuentes_informacion")
public class FuenteInformacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "institucion", length = 150)
    private String institucion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_actividad", nullable = false)
    private TipoActividadAmplio tipoActividad;

    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;

    @Column(name = "url", length = 500)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "formato")
    private FormatoFuente formato = FormatoFuente.OTRO;

    @Column(name = "frecuencia_actualizacion", length = 100)
    private String frecuenciaActualizacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoFuente estado = EstadoFuente.ACTIVA;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @OneToMany(mappedBy = "fuente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DatoFuente> datosFuente = new ArrayList<>();

    public FuenteInformacion() {
    }

    public FuenteInformacion(Long id, String nombre, String institucion, TipoActividadAmplio tipoActividad, 
                             String descripcion, String url, FormatoFuente formato, 
                             String frecuenciaActualizacion, EstadoFuente estado, LocalDateTime fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.institucion = institucion;
        this.tipoActividad = tipoActividad;
        this.descripcion = descripcion;
        this.url = url;
        this.formato = formato != null ? formato : FormatoFuente.OTRO;
        this.frecuenciaActualizacion = frecuenciaActualizacion;
        this.estado = estado != null ? estado : EstadoFuente.ACTIVA;
        this.fechaRegistro = fechaRegistro;
    }

    public static FuenteInformacionBuilder builder() {
        return new FuenteInformacionBuilder();
    }

    public static class FuenteInformacionBuilder {
        private Long id;
        private String nombre;
        private String institucion;
        private TipoActividadAmplio tipoActividad;
        private String descripcion;
        private String url;
        private FormatoFuente formato = FormatoFuente.OTRO;
        private String frecuenciaActualizacion;
        private EstadoFuente estado = EstadoFuente.ACTIVA;
        private LocalDateTime fechaRegistro;

        public FuenteInformacionBuilder id(Long id) { this.id = id; return this; }
        public FuenteInformacionBuilder nombre(String nombre) { this.nombre = nombre; return this; }
        public FuenteInformacionBuilder institucion(String institucion) { this.institucion = institucion; return this; }
        public FuenteInformacionBuilder tipoActividad(TipoActividadAmplio tipoActividad) { this.tipoActividad = tipoActividad; return this; }
        public FuenteInformacionBuilder descripcion(String descripcion) { this.descripcion = descripcion; return this; }
        public FuenteInformacionBuilder url(String url) { this.url = url; return this; }
        public FuenteInformacionBuilder formato(FormatoFuente formato) { this.formato = formato; return this; }
        public FuenteInformacionBuilder frecuenciaActualizacion(String frecuenciaActualizacion) { this.frecuenciaActualizacion = frecuenciaActualizacion; return this; }
        public FuenteInformacionBuilder estado(EstadoFuente estado) { this.estado = estado; return this; }
        public FuenteInformacionBuilder fechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; return this; }

        public FuenteInformacion build() {
            return new FuenteInformacion(id, nombre, institucion, tipoActividad, descripcion, url, formato, frecuenciaActualizacion, estado, fechaRegistro);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getInstitucion() { return institucion; }
    public void setInstitucion(String institucion) { this.institucion = institucion; }

    public TipoActividadAmplio getTipoActividad() { return tipoActividad; }
    public void setTipoActividad(TipoActividadAmplio tipoActividad) { this.tipoActividad = tipoActividad; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public FormatoFuente getFormato() { return formato; }
    public void setFormato(FormatoFuente formato) { this.formato = formato; }

    public String getFrecuenciaActualizacion() { return frecuenciaActualizacion; }
    public void setFrecuenciaActualizacion(String frecuenciaActualizacion) { this.frecuenciaActualizacion = frecuenciaActualizacion; }

    public EstadoFuente getEstado() { return estado; }
    public void setEstado(EstadoFuente estado) { this.estado = estado; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public List<DatoFuente> getDatosFuente() { return datosFuente; }
    public void setDatosFuente(List<DatoFuente> datosFuente) { this.datosFuente = datosFuente; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuenteInformacion that = (FuenteInformacion) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "FuenteInformacion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", institucion='" + institucion + '\'' +
                ", tipoActividad=" + tipoActividad +
                ", formato=" + formato +
                ", estado=" + estado +
                '}';
    }
}
