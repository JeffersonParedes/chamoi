package pe.edu.chamoi.dto.response;

import pe.edu.chamoi.model.enums.NivelPrioridad;
import pe.edu.chamoi.model.enums.NivelRiesgo;
import pe.edu.chamoi.model.enums.OrigenRecomendacion;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ResultadoAnalisisResponse {

    private Long id;
    private String resumen;
    private String interpretacionIa;
    private NivelRiesgo nivelRiesgo;
    private BigDecimal nivelConfianza;
    private String limitaciones;
    private String factoresJson;
    private String fuentesUsadasJson;
    private LocalDateTime fechaGeneracion;
    private List<RecomendacionResponse> recomendaciones;

    public ResultadoAnalisisResponse() {
    }

    public ResultadoAnalisisResponse(Long id, String resumen, String interpretacionIa, 
                                     NivelRiesgo nivelRiesgo, BigDecimal nivelConfianza, 
                                     String limitaciones, String factoresJson, String fuentesUsadasJson, 
                                     LocalDateTime fechaGeneracion, List<RecomendacionResponse> recomendaciones) {
        this.id = id;
        this.resumen = resumen;
        this.interpretacionIa = interpretacionIa;
        this.nivelRiesgo = nivelRiesgo;
        this.nivelConfianza = nivelConfianza;
        this.limitaciones = limitaciones;
        this.factoresJson = factoresJson;
        this.fuentesUsadasJson = fuentesUsadasJson;
        this.fechaGeneracion = fechaGeneracion;
        this.recomendaciones = recomendaciones;
    }

    public static ResultadoAnalisisResponseBuilder builder() {
        return new ResultadoAnalisisResponseBuilder();
    }

    public static class ResultadoAnalisisResponseBuilder {
        private Long id;
        private String resumen;
        private String interpretacionIa;
        private NivelRiesgo nivelRiesgo;
        private BigDecimal nivelConfianza;
        private String limitaciones;
        private String factoresJson;
        private String fuentesUsadasJson;
        private LocalDateTime fechaGeneracion;
        private List<RecomendacionResponse> recomendaciones;

        public ResultadoAnalisisResponseBuilder id(Long id) { this.id = id; return this; }
        public ResultadoAnalisisResponseBuilder resumen(String resumen) { this.resumen = resumen; return this; }
        public ResultadoAnalisisResponseBuilder interpretacionIa(String interpretacionIa) { this.interpretacionIa = interpretacionIa; return this; }
        public ResultadoAnalisisResponseBuilder nivelRiesgo(NivelRiesgo nivelRiesgo) { this.nivelRiesgo = nivelRiesgo; return this; }
        public ResultadoAnalisisResponseBuilder nivelConfianza(BigDecimal nivelConfianza) { this.nivelConfianza = nivelConfianza; return this; }
        public ResultadoAnalisisResponseBuilder limitaciones(String limitaciones) { this.limitaciones = limitaciones; return this; }
        public ResultadoAnalisisResponseBuilder factoresJson(String factoresJson) { this.factoresJson = factoresJson; return this; }
        public ResultadoAnalisisResponseBuilder fuentesUsadasJson(String fuentesUsadasJson) { this.fuentesUsadasJson = fuentesUsadasJson; return this; }
        public ResultadoAnalisisResponseBuilder fechaGeneracion(LocalDateTime fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; return this; }
        public ResultadoAnalisisResponseBuilder recomendaciones(List<RecomendacionResponse> recomendaciones) { this.recomendaciones = recomendaciones; return this; }

        public ResultadoAnalisisResponse build() {
            return new ResultadoAnalisisResponse(id, resumen, interpretacionIa, nivelRiesgo, 
                                                nivelConfianza, limitaciones, factoresJson, 
                                                fuentesUsadasJson, fechaGeneracion, recomendaciones);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public List<RecomendacionResponse> getRecomendaciones() { return recomendaciones; }
    public void setRecomendaciones(List<RecomendacionResponse> recomendaciones) { this.recomendaciones = recomendaciones; }

    public static class RecomendacionResponse {
        private Long id;
        private String titulo;
        private String descripcion;
        private String motivo;
        private NivelPrioridad prioridad;
        private OrigenRecomendacion origen;
        private LocalDateTime fechaGeneracion;

        public RecomendacionResponse() {
        }

        public RecomendacionResponse(Long id, String titulo, String descripcion, String motivo, 
                                     NivelPrioridad prioridad, OrigenRecomendacion origen, 
                                     LocalDateTime fechaGeneracion) {
            this.id = id;
            this.titulo = titulo;
            this.descripcion = descripcion;
            this.motivo = motivo;
            this.prioridad = prioridad;
            this.origen = origen;
            this.fechaGeneracion = fechaGeneracion;
        }

        public static RecomendacionResponseBuilder builder() {
            return new RecomendacionResponseBuilder();
        }

        public static class RecomendacionResponseBuilder {
            private Long id;
            private String titulo;
            private String descripcion;
            private String motivo;
            private NivelPrioridad prioridad;
            private OrigenRecomendacion origen;
            private LocalDateTime fechaGeneracion;

            public RecomendacionResponseBuilder id(Long id) { this.id = id; return this; }
            public RecomendacionResponseBuilder titulo(String titulo) { this.titulo = titulo; return this; }
            public RecomendacionResponseBuilder descripcion(String descripcion) { this.descripcion = descripcion; return this; }
            public RecomendacionResponseBuilder motivo(String motivo) { this.motivo = motivo; return this; }
            public RecomendacionResponseBuilder prioridad(NivelPrioridad prioridad) { this.prioridad = prioridad; return this; }
            public RecomendacionResponseBuilder origen(OrigenRecomendacion origen) { this.origen = origen; return this; }
            public RecomendacionResponseBuilder fechaGeneracion(LocalDateTime fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; return this; }

            public RecomendacionResponse build() {
                return new RecomendacionResponse(id, titulo, descripcion, motivo, prioridad, origen, fechaGeneracion);
            }
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

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
    }
}
