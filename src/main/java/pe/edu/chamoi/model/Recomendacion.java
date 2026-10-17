package pe.edu.chamoi.model;

import jakarta.persistence.*;
import pe.edu.chamoi.model.enums.NivelPrioridad;
import pe.edu.chamoi.model.enums.OrigenRecomendacion;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "recomendaciones")
public class Recomendacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resultado_id", nullable = false)
    private ResultadoAnalisis resultado;

    @Column(name = "titulo", nullable = false, length = 180)
    private String titulo;

    @Column(name = "descripcion", nullable = false, columnDefinition = "text")
    private String descripcion;

    @Column(name = "motivo", columnDefinition = "text")
    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "prioridad")
    private NivelPrioridad prioridad = NivelPrioridad.MEDIA;

    @Enumerated(EnumType.STRING)
    @Column(name = "origen")
    private OrigenRecomendacion origen = OrigenRecomendacion.MIXTO;

    @Column(name = "fecha_generacion")
    private LocalDateTime fechaGeneracion;

    public Recomendacion() {
    }

    public Recomendacion(Long id, ResultadoAnalisis resultado, String titulo, String descripcion, 
                         String motivo, NivelPrioridad prioridad, OrigenRecomendacion origen, 
                         LocalDateTime fechaGeneracion) {
        this.id = id;
        this.resultado = resultado;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.motivo = motivo;
        this.prioridad = prioridad != null ? prioridad : NivelPrioridad.MEDIA;
        this.origen = origen != null ? origen : OrigenRecomendacion.MIXTO;
        this.fechaGeneracion = fechaGeneracion;
    }

    public static RecomendacionBuilder builder() {
        return new RecomendacionBuilder();
    }

    public static class RecomendacionBuilder {
        private Long id;
        private ResultadoAnalisis resultado;
        private String titulo;
        private String descripcion;
        private String motivo;
        private NivelPrioridad prioridad = NivelPrioridad.MEDIA;
        private OrigenRecomendacion origen = OrigenRecomendacion.MIXTO;
        private LocalDateTime fechaGeneracion;

        public RecomendacionBuilder id(Long id) { this.id = id; return this; }
        public RecomendacionBuilder resultado(ResultadoAnalisis resultado) { this.resultado = resultado; return this; }
        public RecomendacionBuilder titulo(String titulo) { this.titulo = titulo; return this; }
        public RecomendacionBuilder descripcion(String descripcion) { this.descripcion = descripcion; return this; }
        public RecomendacionBuilder motivo(String motivo) { this.motivo = motivo; return this; }
        public RecomendacionBuilder prioridad(NivelPrioridad prioridad) { this.prioridad = prioridad; return this; }
        public RecomendacionBuilder origen(OrigenRecomendacion origen) { this.origen = origen; return this; }
        public RecomendacionBuilder fechaGeneracion(LocalDateTime fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; return this; }

        public Recomendacion build() {
            return new Recomendacion(id, resultado, titulo, descripcion, motivo, prioridad, origen, fechaGeneracion);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ResultadoAnalisis getResultado() { return resultado; }
    public void setResultado(ResultadoAnalisis resultado) { this.resultado = resultado; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public NivelPrioridad getPrioridad() { return prioridad; }
    public void setPrioridad(NivelPrioridad prioridad) { this.prioridad = prioridad; }

    public OrigenRecomendacion getOrigen() { return origen; }
    public void setOrigen(OrigenRecomendacion origen) { this.origen = origen; }

    public LocalDateTime getFechaGeneracion() { return fechaGeneracion; }
    public void setFechaGeneracion(LocalDateTime fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recomendacion that = (Recomendacion) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Recomendacion{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", prioridad=" + prioridad +
                ", origen=" + origen +
                ", fechaGeneracion=" + fechaGeneracion +
                '}';
    }
}
