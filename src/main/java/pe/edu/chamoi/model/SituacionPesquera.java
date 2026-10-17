package pe.edu.chamoi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "situaciones_pesqueras")
public class SituacionPesquera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especie_id")
    private Especie especie;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "zona_actividad", length = 255)
    private String zonaActividad;

    @Column(name = "tipo_pesca", length = 100)
    private String tipoPesca;

    @Column(name = "cantidad_estimada", precision = 12, scale = 2)
    private BigDecimal cantidadEstimada;

    @Column(name = "unidad_cantidad", length = 40)
    private String unidadCantidad;

    @Column(name = "condiciones_observadas", columnDefinition = "text")
    private String condicionesObservadas;

    @Column(name = "objetivo", columnDefinition = "text")
    private String objetivo;

    @Column(name = "observaciones", columnDefinition = "text")
    private String observaciones;

    public SituacionPesquera() {
    }

    public SituacionPesquera(Long id, Usuario usuario, Especie especie, LocalDateTime fechaRegistro, 
                             String zonaActividad, String tipoPesca, BigDecimal cantidadEstimada, 
                             String unidadCantidad, String condicionesObservadas, String objetivo, 
                             String observaciones) {
        this.id = id;
        this.usuario = usuario;
        this.especie = especie;
        this.fechaRegistro = fechaRegistro;
        this.zonaActividad = zonaActividad;
        this.tipoPesca = tipoPesca;
        this.cantidadEstimada = cantidadEstimada;
        this.unidadCantidad = unidadCantidad;
        this.condicionesObservadas = condicionesObservadas;
        this.objetivo = objetivo;
        this.observaciones = observaciones;
    }

    public static SituacionPesqueraBuilder builder() {
        return new SituacionPesqueraBuilder();
    }

    public static class SituacionPesqueraBuilder {
        private Long id;
        private Usuario usuario;
        private Especie especie;
        private LocalDateTime fechaRegistro;
        private String zonaActividad;
        private String tipoPesca;
        private BigDecimal cantidadEstimada;
        private String unidadCantidad;
        private String condicionesObservadas;
        private String objetivo;
        private String observaciones;

        public SituacionPesqueraBuilder id(Long id) { this.id = id; return this; }
        public SituacionPesqueraBuilder usuario(Usuario usuario) { this.usuario = usuario; return this; }
        public SituacionPesqueraBuilder especie(Especie especie) { this.especie = especie; return this; }
        public SituacionPesqueraBuilder fechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; return this; }
        public SituacionPesqueraBuilder zonaActividad(String zonaActividad) { this.zonaActividad = zonaActividad; return this; }
        public SituacionPesqueraBuilder tipoPesca(String tipoPesca) { this.tipoPesca = tipoPesca; return this; }
        public SituacionPesqueraBuilder cantidadEstimada(BigDecimal cantidadEstimada) { this.cantidadEstimada = cantidadEstimada; return this; }
        public SituacionPesqueraBuilder unidadCantidad(String unidadCantidad) { this.unidadCantidad = unidadCantidad; return this; }
        public SituacionPesqueraBuilder condicionesObservadas(String condicionesObservadas) { this.condicionesObservadas = condicionesObservadas; return this; }
        public SituacionPesqueraBuilder objetivo(String objetivo) { this.objetivo = objetivo; return this; }
        public SituacionPesqueraBuilder observaciones(String observaciones) { this.observaciones = observaciones; return this; }

        public SituacionPesquera build() {
            return new SituacionPesquera(id, usuario, especie, fechaRegistro, zonaActividad, 
                                         tipoPesca, cantidadEstimada, unidadCantidad, condicionesObservadas, 
                                         objetivo, observaciones);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Especie getEspecie() { return especie; }
    public void setEspecie(Especie especie) { this.especie = especie; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

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

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SituacionPesquera that = (SituacionPesquera) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SituacionPesquera{" +
                "id=" + id +
                ", zonaActividad='" + zonaActividad + '\'' +
                ", tipoPesca='" + tipoPesca + '\'' +
                ", cantidadEstimada=" + cantidadEstimada +
                ", unidadCantidad='" + unidadCantidad + '\'' +
                '}';
    }
}
