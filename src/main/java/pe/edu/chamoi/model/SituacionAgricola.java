package pe.edu.chamoi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "situaciones_agricolas")
public class SituacionAgricola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cultivo_id")
    private Cultivo cultivo;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "etapa_cultivo", length = 100)
    private String etapaCultivo;

    @Column(name = "area_cultivada", precision = 12, scale = 2)
    private BigDecimal areaCultivada;

    @Column(name = "unidad_area", length = 30)
    private String unidadArea;

    @Column(name = "tipo_suelo", length = 100)
    private String tipoSuelo;

    @Column(name = "disponibilidad_agua", length = 100)
    private String disponibilidadAgua;

    @Column(name = "problema_principal", columnDefinition = "text")
    private String problemaPrincipal;

    @Column(name = "observaciones", columnDefinition = "text")
    private String observaciones;

    public SituacionAgricola() {
    }

    public SituacionAgricola(Long id, Usuario usuario, Cultivo cultivo, LocalDateTime fechaRegistro, 
                             String etapaCultivo, BigDecimal areaCultivada, String unidadArea, 
                             String tipoSuelo, String disponibilidadAgua, String problemaPrincipal, 
                             String observaciones) {
        this.id = id;
        this.usuario = usuario;
        this.cultivo = cultivo;
        this.fechaRegistro = fechaRegistro;
        this.etapaCultivo = etapaCultivo;
        this.areaCultivada = areaCultivada;
        this.unidadArea = unidadArea;
        this.tipoSuelo = tipoSuelo;
        this.disponibilidadAgua = disponibilidadAgua;
        this.problemaPrincipal = problemaPrincipal;
        this.observaciones = observaciones;
    }

    public static SituacionAgricolaBuilder builder() {
        return new SituacionAgricolaBuilder();
    }

    public static class SituacionAgricolaBuilder {
        private Long id;
        private Usuario usuario;
        private Cultivo cultivo;
        private LocalDateTime fechaRegistro;
        private String etapaCultivo;
        private BigDecimal areaCultivada;
        private String unidadArea;
        private String tipoSuelo;
        private String disponibilidadAgua;
        private String problemaPrincipal;
        private String observaciones;

        public SituacionAgricolaBuilder id(Long id) { this.id = id; return this; }
        public SituacionAgricolaBuilder usuario(Usuario usuario) { this.usuario = usuario; return this; }
        public SituacionAgricolaBuilder cultivo(Cultivo cultivo) { this.cultivo = cultivo; return this; }
        public SituacionAgricolaBuilder fechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; return this; }
        public SituacionAgricolaBuilder etapaCultivo(String etapaCultivo) { this.etapaCultivo = etapaCultivo; return this; }
        public SituacionAgricolaBuilder areaCultivada(BigDecimal areaCultivada) { this.areaCultivada = areaCultivada; return this; }
        public SituacionAgricolaBuilder unidadArea(String unidadArea) { this.unidadArea = unidadArea; return this; }
        public SituacionAgricolaBuilder tipoSuelo(String tipoSuelo) { this.tipoSuelo = tipoSuelo; return this; }
        public SituacionAgricolaBuilder disponibilidadAgua(String disponibilidadAgua) { this.disponibilidadAgua = disponibilidadAgua; return this; }
        public SituacionAgricolaBuilder problemaPrincipal(String problemaPrincipal) { this.problemaPrincipal = problemaPrincipal; return this; }
        public SituacionAgricolaBuilder observaciones(String observaciones) { this.observaciones = observaciones; return this; }

        public SituacionAgricola build() {
            return new SituacionAgricola(id, usuario, cultivo, fechaRegistro, etapaCultivo, areaCultivada, 
                                         unidadArea, tipoSuelo, disponibilidadAgua, problemaPrincipal, observaciones);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Cultivo getCultivo() { return cultivo; }
    public void setCultivo(Cultivo cultivo) { this.cultivo = cultivo; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

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

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SituacionAgricola that = (SituacionAgricola) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SituacionAgricola{" +
                "id=" + id +
                ", etapaCultivo='" + etapaCultivo + '\'' +
                ", areaCultivada=" + areaCultivada +
                ", unidadArea='" + unidadArea + '\'' +
                '}';
    }
}
