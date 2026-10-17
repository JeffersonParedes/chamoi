package pe.edu.chamoi.dto.request;

import java.math.BigDecimal;

public class AnalisisRequest {

    // Común
    private String pregunta;
    private String observaciones;

    // Campos AGRICOLA
    private Long cultivoId;
    private String etapaCultivo;
    private BigDecimal areaCultivada;
    private String unidadArea;
    private String tipoSuelo;
    private String disponibilidadAgua;
    private String problemaPrincipal;

    // Campos PESQUERA
    private Long especieId;
    private String zonaActividad;
    private String tipoPesca;
    private BigDecimal cantidadEstimada;
    private String unidadCantidad;
    private String condicionesObservadas;
    private String objetivo;

    public AnalisisRequest() {
    }

    public AnalisisRequest(String pregunta, String observaciones, Long cultivoId, String etapaCultivo, 
                           BigDecimal areaCultivada, String unidadArea, String tipoSuelo, 
                           String disponibilidadAgua, String problemaPrincipal, Long especieId, 
                           String zonaActividad, String tipoPesca, BigDecimal cantidadEstimada, 
                           String unidadCantidad, String condicionesObservadas, String objetivo) {
        this.pregunta = pregunta;
        this.observaciones = observaciones;
        this.cultivoId = cultivoId;
        this.etapaCultivo = etapaCultivo;
        this.areaCultivada = areaCultivada;
        this.unidadArea = unidadArea;
        this.tipoSuelo = tipoSuelo;
        this.disponibilidadAgua = disponibilidadAgua;
        this.problemaPrincipal = problemaPrincipal;
        this.especieId = especieId;
        this.zonaActividad = zonaActividad;
        this.tipoPesca = tipoPesca;
        this.cantidadEstimada = cantidadEstimada;
        this.unidadCantidad = unidadCantidad;
        this.condicionesObservadas = condicionesObservadas;
        this.objetivo = objetivo;
    }

    public static AnalisisRequestBuilder builder() {
        return new AnalisisRequestBuilder();
    }

    public static class AnalisisRequestBuilder {
        private String pregunta;
        private String observaciones;
        private Long cultivoId;
        private String etapaCultivo;
        private BigDecimal areaCultivada;
        private String unidadArea;
        private String tipoSuelo;
        private String disponibilidadAgua;
        private String problemaPrincipal;
        private Long especieId;
        private String zonaActividad;
        private String tipoPesca;
        private BigDecimal cantidadEstimada;
        private String unidadCantidad;
        private String condicionesObservadas;
        private String objetivo;

        public AnalisisRequestBuilder pregunta(String pregunta) { this.pregunta = pregunta; return this; }
        public AnalisisRequestBuilder observaciones(String observaciones) { this.observaciones = observaciones; return this; }
        public AnalisisRequestBuilder cultivoId(Long cultivoId) { this.cultivoId = cultivoId; return this; }
        public AnalisisRequestBuilder etapaCultivo(String etapaCultivo) { this.etapaCultivo = etapaCultivo; return this; }
        public AnalisisRequestBuilder areaCultivada(BigDecimal areaCultivada) { this.areaCultivada = areaCultivada; return this; }
        public AnalisisRequestBuilder unidadArea(String unidadArea) { this.unidadArea = unidadArea; return this; }
        public AnalisisRequestBuilder tipoSuelo(String tipoSuelo) { this.tipoSuelo = tipoSuelo; return this; }
        public AnalisisRequestBuilder disponibilidadAgua(String disponibilidadAgua) { this.disponibilidadAgua = disponibilidadAgua; return this; }
        public AnalisisRequestBuilder problemaPrincipal(String problemaPrincipal) { this.problemaPrincipal = problemaPrincipal; return this; }
        public AnalisisRequestBuilder especieId(Long especieId) { this.especieId = especieId; return this; }
        public AnalisisRequestBuilder zonaActividad(String zonaActividad) { this.zonaActividad = zonaActividad; return this; }
        public AnalisisRequestBuilder tipoPesca(String tipoPesca) { this.tipoPesca = tipoPesca; return this; }
        public AnalisisRequestBuilder cantidadEstimada(BigDecimal cantidadEstimada) { this.cantidadEstimada = cantidadEstimada; return this; }
        public AnalisisRequestBuilder unidadCantidad(String unidadCantidad) { this.unidadCantidad = unidadCantidad; return this; }
        public AnalisisRequestBuilder condicionesObservadas(String condicionesObservadas) { this.condicionesObservadas = condicionesObservadas; return this; }
        public AnalisisRequestBuilder objetivo(String objetivo) { this.objetivo = objetivo; return this; }

        public AnalisisRequest build() {
            return new AnalisisRequest(pregunta, observaciones, cultivoId, etapaCultivo, areaCultivada, 
                                       unidadArea, tipoSuelo, disponibilidadAgua, problemaPrincipal, 
                                       especieId, zonaActividad, tipoPesca, cantidadEstimada, 
                                       unidadCantidad, condicionesObservadas, objetivo);
        }
    }

    public String getPregunta() { return pregunta; }
    public void setPregunta(String pregunta) { this.pregunta = pregunta; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public Long getCultivoId() { return cultivoId; }
    public void setCultivoId(Long cultivoId) { this.cultivoId = cultivoId; }

    public String getEtapaCultivo() { return etapaCultivo; }
    public void setEtapaCultivo(String etapaCultivo) { this.etapaCultivo = etapaCultivo; }

    public BigDecimal getAreaCultivada() { return areaCultivada; }
    public void setAreaCultivada(BigDecimal areaCultivada) { this.areaCultivada = areaCultivada; }

    public String getUnidadArea() { return unidadArea; }
    public void setUnidadArea(String unidadArea) { this.unidadArea = unidadArea; }

    public String getTipoSuelo() { return tipoSuelo; }
    public void setTipoSuelo(String tipoSuelo) { this.tipoSuelo = tipoSuelo; }

    public String getDisponibilidadAgua() { return disponibilidadAgua; }
    public void setDisponibilidadAgua(String disponibilidadAgua) { this.disponibilidadAgua = disponibilidadAgua; }

    public String getProblemaPrincipal() { return problemaPrincipal; }
    public void setProblemaPrincipal(String problemaPrincipal) { this.problemaPrincipal = problemaPrincipal; }

    public Long getEspecieId() { return especieId; }
    public void setEspecieId(Long especieId) { this.especieId = especieId; }

    public String getZonaActividad() { return zonaActividad; }
    public void setZonaActividad(String zonaActividad) { this.zonaActividad = zonaActividad; }

    public String getTipoPesca() { return tipoPesca; }
    public void setTipoPesca(String tipoPesca) { this.tipoPesca = tipoPesca; }

    public BigDecimal getCantidadEstimada() { return cantidadEstimada; }
    public void setCantidadEstimada(BigDecimal cantidadEstimada) { this.cantidadEstimada = cantidadEstimada; }

    public String getUnidadCantidad() { return unidadCantidad; }
    public void setUnidadCantidad(String unidadCantidad) { this.unidadCantidad = unidadCantidad; }

    public String getCondicionesObservadas() { return condicionesObservadas; }
    public void setCondicionesObservadas(String condicionesObservadas) { this.condicionesObservadas = condicionesObservadas; }

    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }
}
