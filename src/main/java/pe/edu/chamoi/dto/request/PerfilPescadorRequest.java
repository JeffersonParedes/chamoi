package pe.edu.chamoi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PerfilPescadorRequest {

    @NotBlank(message = "El departamento es obligatorio")
    @Size(max = 100, message = "El departamento no debe exceder 100 caracteres")
    private String departamento;

    @NotBlank(message = "La provincia es obligatoria")
    @Size(max = 100, message = "La provincia no debe exceder 100 caracteres")
    private String provincia;

    @Size(max = 255, message = "La zona de actividad no debe exceder 255 caracteres")
    private String zonaActividad;

    @Size(max = 100, message = "El tipo de pesca no debe exceder 100 caracteres")
    private String tipoPesca;

    @Size(max = 100, message = "La embarcación no debe exceder 100 caracteres")
    private String embarcacion;

    private String situacionActual;

    public PerfilPescadorRequest() {
    }

    public PerfilPescadorRequest(String departamento, String provincia, String zonaActividad, 
                                String tipoPesca, String embarcacion, String situacionActual) {
        this.departamento = departamento;
        this.provincia = provincia;
        this.zonaActividad = zonaActividad;
        this.tipoPesca = tipoPesca;
        this.embarcacion = embarcacion;
        this.situacionActual = situacionActual;
    }

    public static PerfilPescadorRequestBuilder builder() {
        return new PerfilPescadorRequestBuilder();
    }

    public static class PerfilPescadorRequestBuilder {
        private String departamento;
        private String provincia;
        private String zonaActividad;
        private String tipoPesca;
        private String embarcacion;
        private String situacionActual;

        public PerfilPescadorRequestBuilder departamento(String departamento) { this.departamento = departamento; return this; }
        public PerfilPescadorRequestBuilder provincia(String provincia) { this.provincia = provincia; return this; }
        public PerfilPescadorRequestBuilder zonaActividad(String zonaActividad) { this.zonaActividad = zonaActividad; return this; }
        public PerfilPescadorRequestBuilder tipoPesca(String tipoPesca) { this.tipoPesca = tipoPesca; return this; }
        public PerfilPescadorRequestBuilder embarcacion(String embarcacion) { this.embarcacion = embarcacion; return this; }
        public PerfilPescadorRequestBuilder situacionActual(String situacionActual) { this.situacionActual = situacionActual; return this; }

        public PerfilPescadorRequest build() {
            return new PerfilPescadorRequest(departamento, provincia, zonaActividad, tipoPesca, embarcacion, situacionActual);
        }
    }

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
}
