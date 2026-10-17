package pe.edu.chamoi.model;

import jakarta.persistence.*;
import pe.edu.chamoi.model.enums.NivelRiesgo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "resultados_analisis")
public class ResultadoAnalisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analisis_id", nullable = false, unique = true)
    private Analisis analisis;

    @Column(name = "resumen", nullable = false, columnDefinition = "text")
    private String resumen;

    @Column(name = "interpretacion_ia", columnDefinition = "text")
    private String interpretacionIa;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_riesgo")
    private NivelRiesgo nivelRiesgo = NivelRiesgo.NO_DETERMINADO;

    @Column(name = "nivel_confianza", precision = 5, scale = 2)
    private BigDecimal nivelConfianza;

    @Column(name = "limitaciones", columnDefinition = "text")
    private String limitaciones;

    @Column(name = "factores_json", columnDefinition = "json")
    private String factoresJson;

    @Column(name = "fuentes_usadas_json", columnDefinition = "json")
    private String fuentesUsadasJson;

    @Column(name = "fecha_generacion")
    private LocalDateTime fechaGeneracion;

    @OneToMany(mappedBy = "resultado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recomendacion> recomendaciones = new ArrayList<>();

    public ResultadoAnalisis() {
    }

    public ResultadoAnalisis(Long id, Analisis analisis, String resumen, String interpretacionIa, 
                             NivelRiesgo nivelRiesgo, BigDecimal nivelConfianza, String limitaciones, 
                             String factoresJson, String fuentesUsadasJson, LocalDateTime fechaGeneracion) {
        this.id = id;
        this.analisis = analisis;
        this.resumen = resumen;
        this.interpretacionIa = interpretacionIa;
        this.nivelRiesgo = nivelRiesgo != null ? nivelRiesgo : NivelRiesgo.NO_DETERMINADO;
        this.nivelConfianza = nivelConfianza;
        this.limitaciones = limitaciones;
        this.factoresJson = factoresJson;
        this.fuentesUsadasJson = fuentesUsadasJson;
        this.fechaGeneracion = fechaGeneracion;
    }

    public static ResultadoAnalisisBuilder builder() {
        return new ResultadoAnalisisBuilder();
    }

    public static class ResultadoAnalisisBuilder {
        private Long id;
        private Analisis analisis;
        private String resumen;
        private String interpretacionIa;
        private NivelRiesgo nivelRiesgo = NivelRiesgo.NO_DETERMINADO;
        private BigDecimal nivelConfianza;
        private String limitaciones;
        private String factoresJson;
        private String fuentesUsadasJson;
        private LocalDateTime fechaGeneracion;

        public ResultadoAnalisisBuilder id(Long id) { this.id = id; return this; }
        public ResultadoAnalisisBuilder analisis(Analisis analisis) { this.analisis = analisis; return this; }
        public ResultadoAnalisisBuilder resumen(String resumen) { this.resumen = resumen; return this; }
        public ResultadoAnalisisBuilder interpretacionIa(String interpretacionIa) { this.interpretacionIa = interpretacionIa; return this; }
        public ResultadoAnalisisBuilder nivelRiesgo(NivelRiesgo nivelRiesgo) { this.nivelRiesgo = nivelRiesgo; return this; }
        public ResultadoAnalisisBuilder nivelConfianza(BigDecimal nivelConfianza) { this.nivelConfianza = nivelConfianza; return this; }
        public ResultadoAnalisisBuilder limitaciones(String limitaciones) { this.limitaciones = limitaciones; return this; }
        public ResultadoAnalisisBuilder factoresJson(String factoresJson) { this.factoresJson = factoresJson; return this; }
        public ResultadoAnalisisBuilder fuentesUsadasJson(String fuentesUsadasJson) { this.fuentesUsadasJson = fuentesUsadasJson; return this; }
        public ResultadoAnalisisBuilder fechaGeneracion(LocalDateTime fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; return this; }

        public ResultadoAnalisis build() {
            return new ResultadoAnalisis(id, analisis, resumen, interpretacionIa, nivelRiesgo, 
                                         nivelConfianza, limitaciones, factoresJson, fuentesUsadasJson, fechaGeneracion);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Analisis getAnalisis() { return analisis; }
    public void setAnalisis(Analisis analisis) { this.analisis = analisis; }

    public String getResumen() { return resumen; }
    public void setResumen(String resumen) { this.resumen = resumen; }

    public String getInterpretacionIa() { return interpretacionIa; }
    public void setInterpretacionIa(String interpretacionIa) { this.interpretacionIa = interpretacionIa; }

    public NivelRiesgo getNivelRiesgo() { return nivelRiesgo; }
    public void setNivelRiesgo(NivelRiesgo nivelRiesgo) { this.nivelRiesgo = nivelRiesgo; }

    public BigDecimal getNivelConfianza() { return nivelConfianza; }
    public void setNivelConfianza(BigDecimal nivelConfianza) { this.nivelConfianza = nivelConfianza; }

    public String getLimitaciones() { return limitaciones; }
    public void setLimitaciones(String limitaciones) { this.limitaciones = limitaciones; }

    public String getFactoresJson() { return factoresJson; }
    public void setFactoresJson(String factoresJson) { this.factoresJson = factoresJson; }

    public String getFuentesUsadasJson() { return fuentesUsadasJson; }
    public void setFuentesUsadasJson(String fuentesUsadasJson) { this.fuentesUsadasJson = fuentesUsadasJson; }

    public LocalDateTime getFechaGeneracion() { return fechaGeneracion; }
    public void setFechaGeneracion(LocalDateTime fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; }

    public List<Recomendacion> getRecomendaciones() { return recomendaciones; }
    public void setRecomendaciones(List<Recomendacion> recomendaciones) { this.recomendaciones = recomendaciones; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultadoAnalisis that = (ResultadoAnalisis) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ResultadoAnalisis{" +
                "id=" + id +
                ", nivelRiesgo=" + nivelRiesgo +
                ", nivelConfianza=" + nivelConfianza +
                ", fechaGeneracion=" + fechaGeneracion +
                '}';
    }
}
