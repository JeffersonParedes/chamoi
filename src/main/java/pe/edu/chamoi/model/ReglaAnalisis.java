package pe.edu.chamoi.model;

import jakarta.persistence.*;
import pe.edu.chamoi.model.enums.EstadoRegla;
import pe.edu.chamoi.model.enums.TipoActividadAmplio;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "reglas_analisis")
public class ReglaAnalisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_actividad", nullable = false)
    private TipoActividadAmplio tipoActividad;

    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;

    @Column(name = "condicion", nullable = false, columnDefinition = "text")
    private String condicion;

    @Column(name = "resultado_esperado", columnDefinition = "text")
    private String resultadoEsperado;

    @Column(name = "prioridad")
    private Integer prioridad = 1;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoRegla estado = EstadoRegla.ACTIVA;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    public ReglaAnalisis() {
    }

    public ReglaAnalisis(Long id, String nombre, TipoActividadAmplio tipoActividad, String descripcion, 
                         String condicion, String resultadoEsperado, Integer prioridad, 
                         EstadoRegla estado, LocalDateTime fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.tipoActividad = tipoActividad;
        this.descripcion = descripcion;
        this.condicion = condicion;
        this.resultadoEsperado = resultadoEsperado;
        this.prioridad = prioridad != null ? prioridad : 1;
        this.estado = estado != null ? estado : EstadoRegla.ACTIVA;
        this.fechaCreacion = fechaCreacion;
    }

    public static ReglaAnalisisBuilder builder() {
        return new ReglaAnalisisBuilder();
    }

    public static class ReglaAnalisisBuilder {
        private Long id;
        private String nombre;
        private TipoActividadAmplio tipoActividad;
        private String descripcion;
        private String condicion;
        private String resultadoEsperado;
        private Integer prioridad = 1;
        private EstadoRegla estado = EstadoRegla.ACTIVA;
        private LocalDateTime fechaCreacion;

        public ReglaAnalisisBuilder id(Long id) { this.id = id; return this; }
        public ReglaAnalisisBuilder nombre(String nombre) { this.nombre = nombre; return this; }
        public ReglaAnalisisBuilder tipoActividad(TipoActividadAmplio tipoActividad) { this.tipoActividad = tipoActividad; return this; }
        public ReglaAnalisisBuilder descripcion(String descripcion) { this.descripcion = descripcion; return this; }
        public ReglaAnalisisBuilder condicion(String condicion) { this.condicion = condicion; return this; }
        public ReglaAnalisisBuilder resultadoEsperado(String resultadoEsperado) { this.resultadoEsperado = resultadoEsperado; return this; }
        public ReglaAnalisisBuilder prioridad(Integer prioridad) { this.prioridad = prioridad; return this; }
        public ReglaAnalisisBuilder estado(EstadoRegla estado) { this.estado = estado; return this; }
        public ReglaAnalisisBuilder fechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; return this; }

        public ReglaAnalisis build() {
            return new ReglaAnalisis(id, nombre, tipoActividad, descripcion, condicion, resultadoEsperado, prioridad, estado, fechaCreacion);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public TipoActividadAmplio getTipoActividad() { return tipoActividad; }
    public void setTipoActividad(TipoActividadAmplio tipoActividad) { this.tipoActividad = tipoActividad; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getCondicion() { return condicion; }
    public void setCondicion(String condicion) { this.condicion = condicion; }

    public String getResultadoEsperado() { return resultadoEsperado; }
    public void setResultadoEsperado(String resultadoEsperado) { this.resultadoEsperado = resultadoEsperado; }

    public Integer getPrioridad() { return prioridad; }
    public void setPrioridad(Integer prioridad) { this.prioridad = prioridad; }

    public EstadoRegla getEstado() { return estado; }
    public void setEstado(EstadoRegla estado) { this.estado = estado; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReglaAnalisis that = (ReglaAnalisis) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ReglaAnalisis{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipoActividad=" + tipoActividad +
                ", prioridad=" + prioridad +
                ", estado=" + estado +
                '}';
    }
}
