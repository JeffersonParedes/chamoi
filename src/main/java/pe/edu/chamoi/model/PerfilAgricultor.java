package pe.edu.chamoi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "perfiles_agricultores")
public class PerfilAgricultor {

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

    @Column(name = "distrito", length = 100)
    private String distrito;

    @Column(name = "ubicacion_detalle", length = 255)
    private String ubicacionDetalle;

    @Column(name = "area_terreno", precision = 12, scale = 2)
    private BigDecimal areaTerreno;

    @Column(name = "unidad_area", length = 30)
    private String unidadArea;

    @Column(name = "tipo_terreno", length = 100)
    private String tipoTerreno;

    @Column(name = "sistema_riego", length = 100)
    private String sistemaRiego;

    @Column(name = "objetivo", length = 255)
    private String objetivo;

    @Column(name = "situacion_actual", columnDefinition = "text")
    private String situacionActual;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    public PerfilAgricultor() {
    }

    public PerfilAgricultor(Long id, Usuario usuario, String departamento, String provincia, String distrito, 
                            String ubicacionDetalle, BigDecimal areaTerreno, String unidadArea, String tipoTerreno, 
                            String sistemaRiego, String objetivo, String situacionActual, LocalDateTime fechaActualizacion) {
        this.id = id;
        this.usuario = usuario;
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
        this.fechaActualizacion = fechaActualizacion;
    }

    public static PerfilAgricultorBuilder builder() {
        return new PerfilAgricultorBuilder();
    }

    public static class PerfilAgricultorBuilder {
        private Long id;
        private Usuario usuario;
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
        private LocalDateTime fechaActualizacion;

        public PerfilAgricultorBuilder id(Long id) { this.id = id; return this; }
        public PerfilAgricultorBuilder usuario(Usuario usuario) { this.usuario = usuario; return this; }
        public PerfilAgricultorBuilder departamento(String departamento) { this.departamento = departamento; return this; }
        public PerfilAgricultorBuilder provincia(String provincia) { this.provincia = provincia; return this; }
        public PerfilAgricultorBuilder distrito(String distrito) { this.distrito = distrito; return this; }
        public PerfilAgricultorBuilder ubicacionDetalle(String ubicacionDetalle) { this.ubicacionDetalle = ubicacionDetalle; return this; }
        public PerfilAgricultorBuilder areaTerreno(BigDecimal areaTerreno) { this.areaTerreno = areaTerreno; return this; }
        public PerfilAgricultorBuilder unidadArea(String unidadArea) { this.unidadArea = unidadArea; return this; }
        public PerfilAgricultorBuilder tipoTerreno(String tipoTerreno) { this.tipoTerreno = tipoTerreno; return this; }
        public PerfilAgricultorBuilder sistemaRiego(String sistemaRiego) { this.sistemaRiego = sistemaRiego; return this; }
        public PerfilAgricultorBuilder objetivo(String objetivo) { this.objetivo = objetivo; return this; }
        public PerfilAgricultorBuilder situacionActual(String situacionActual) { this.situacionActual = situacionActual; return this; }
        public PerfilAgricultorBuilder fechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; return this; }

        public PerfilAgricultor build() {
            return new PerfilAgricultor(id, usuario, departamento, provincia, distrito, ubicacionDetalle, 
                                        areaTerreno, unidadArea, tipoTerreno, sistemaRiego, objetivo, 
                                        situacionActual, fechaActualizacion);
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

    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerfilAgricultor that = (PerfilAgricultor) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PerfilAgricultor{" +
                "id=" + id +
                ", departamento='" + departamento + '\'' +
                ", provincia='" + provincia + '\'' +
                ", distrito='" + distrito + '\'' +
                ", areaTerreno=" + areaTerreno +
                ", unidadArea='" + unidadArea + '\'' +
                '}';
    }
}
