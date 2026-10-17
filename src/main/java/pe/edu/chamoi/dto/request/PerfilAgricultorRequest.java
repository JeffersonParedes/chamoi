package pe.edu.chamoi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class PerfilAgricultorRequest {

    @NotBlank(message = "El departamento es obligatorio")
    @Size(max = 100, message = "El departamento no debe exceder 100 caracteres")
    private String departamento;

    @NotBlank(message = "La provincia es obligatoria")
    @Size(max = 100, message = "La provincia no debe exceder 100 caracteres")
    private String provincia;

    @Size(max = 100, message = "El distrito no debe exceder 100 caracteres")
    private String distrito;

    @Size(max = 255, message = "La ubicación detallada no debe exceder 255 caracteres")
    private String ubicacionDetalle;

    private BigDecimal areaTerreno;

    @Size(max = 30, message = "La unidad de área no debe exceder 30 caracteres")
    private String unidadArea;

    @Size(max = 100, message = "El tipo de terreno no debe exceder 100 caracteres")
    private String tipoTerreno;

    @Size(max = 100, message = "El sistema de riego no debe exceder 100 caracteres")
    private String sistemaRiego;

    @Size(max = 255, message = "El objetivo no debe exceder 255 caracteres")
    private String objetivo;

    private String situacionActual;

    public PerfilAgricultorRequest() {
    }

    public PerfilAgricultorRequest(String departamento, String provincia, String distrito, 
                                  String ubicacionDetalle, BigDecimal areaTerreno, String unidadArea, 
                                  String tipoTerreno, String sistemaRiego, String objetivo, String situacionActual) {
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
        this.ubicacionDetalle = ubicacionDetalle;
        this.areaTerreno = areaTerreno;
        this.unidadArea = unidadArea;
        this.tipoTerreno = tipoTerreno;
        this.sistemaRiego = sistemaRiego;
        this.objetivo = objetivo;
        this.situacionActual = situacionActual;
    }

    public static PerfilAgricultorRequestBuilder builder() {
        return new PerfilAgricultorRequestBuilder();
    }

    public static class PerfilAgricultorRequestBuilder {
        private String departamento;
        private String provincia;
        private String distrito;
        private String ubicacionDetalle;
        private BigDecimal areaTerreno;
        private String unidadArea;
        private String tipoTerreno;
        private String sistemaRiego;
        private String objetivo;
        private String situacionActual;

        public PerfilAgricultorRequestBuilder departamento(String departamento) { this.departamento = departamento; return this; }
        public PerfilAgricultorRequestBuilder provincia(String provincia) { this.provincia = provincia; return this; }
        public PerfilAgricultorRequestBuilder distrito(String distrito) { this.distrito = distrito; return this; }
        public PerfilAgricultorRequestBuilder ubicacionDetalle(String ubicacionDetalle) { this.ubicacionDetalle = ubicacionDetalle; return this; }
        public PerfilAgricultorRequestBuilder areaTerreno(BigDecimal areaTerreno) { this.areaTerreno = areaTerreno; return this; }
        public PerfilAgricultorRequestBuilder unidadArea(String unidadArea) { this.unidadArea = unidadArea; return this; }
        public PerfilAgricultorRequestBuilder tipoTerreno(String tipoTerreno) { this.tipoTerreno = tipoTerreno; return this; }
        public PerfilAgricultorRequestBuilder sistemaRiego(String sistemaRiego) { this.sistemaRiego = sistemaRiego; return this; }
        public PerfilAgricultorRequestBuilder objetivo(String objetivo) { this.objetivo = objetivo; return this; }
        public PerfilAgricultorRequestBuilder situacionActual(String situacionActual) { this.situacionActual = situacionActual; return this; }

        public PerfilAgricultorRequest build() {
            return new PerfilAgricultorRequest(departamento, provincia, distrito, ubicacionDetalle, 
                                             areaTerreno, unidadArea, tipoTerreno, sistemaRiego, objetivo, situacionActual);
        }
    }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }

    public String getDistrito() { return distrito; }
    public void setDistrito(String distrito) { this.distrito = distrito; }

    public String getUbicacionDetalle() { return ubicacionDetalle; }
    public void setUbicacionDetalle(String ubicacionDetalle) { this.ubicacionDetalle = ubicacionDetalle; }

    public BigDecimal getAreaTerreno() { return areaTerreno; }
    public void setAreaTerreno(BigDecimal areaTerreno) { this.areaTerreno = areaTerreno; }

    public String getUnidadArea() { return unidadArea; }
    public void setUnidadArea(String unidadArea) { this.unidadArea = unidadArea; }

    public String getTipoTerreno() { return tipoTerreno; }
    public void setTipoTerreno(String tipoTerreno) { this.tipoTerreno = tipoTerreno; }

    public String getSistemaRiego() { return sistemaRiego; }
    public void setSistemaRiego(String sistemaRiego) { this.sistemaRiego = sistemaRiego; }

    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }

    public String getSituacionActual() { return situacionActual; }
    public void setSituacionActual(String situacionActual) { this.situacionActual = situacionActual; }
}
