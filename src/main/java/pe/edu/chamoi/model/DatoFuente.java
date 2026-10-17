package pe.edu.chamoi.model;

import jakarta.persistence.*;
import pe.edu.chamoi.model.enums.EstadoValidacion;
import pe.edu.chamoi.model.enums.TipoActividad;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "datos_fuente")
public class DatoFuente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fuente_id", nullable = false)
    private FuenteInformacion fuente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_carga_id")
    private Usuario usuarioCarga;

    @Column(name = "nombre_archivo", length = 255)
    private String nombreArchivo;

    @Column(name = "fecha_dato")
    private LocalDateTime fechaDato;

    @Column(name = "ubicacion", length = 255)
    private String ubicacion;

    @Column(name = "ubicacion_normalizada", length = 300)
    private String ubicacionNormalizada;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_actividad", nullable = false)
    private TipoActividad tipoActividad;

    @Column(name = "indicador", length = 120)
    private String indicador;

    @Column(name = "valor", precision = 18, scale = 6)
    private BigDecimal valor;

    @Column(name = "unidad", length = 40)
    private String unidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_validacion")
    private EstadoValidacion estadoValidacion = EstadoValidacion.PENDIENTE;

    @Column(name = "observaciones", columnDefinition = "text")
    private String observaciones;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    public DatoFuente() {
    }

    public DatoFuente(Long id, FuenteInformacion fuente, Usuario usuarioCarga, String nombreArchivo, 
                      LocalDateTime fechaDato, String ubicacion, String ubicacionNormalizada, 
                      TipoActividad tipoActividad, String indicador, BigDecimal valor, 
                      String unidad, EstadoValidacion estadoValidacion, String observaciones, 
                      LocalDateTime fechaRegistro) {
        this.id = id;
        this.fuente = fuente;
        this.usuarioCarga = usuarioCarga;
        this.nombreArchivo = nombreArchivo;
        this.fechaDato = fechaDato;
        this.ubicacion = ubicacion;
        this.ubicacionNormalizada = ubicacionNormalizada;
        this.tipoActividad = tipoActividad;
        this.indicador = indicador;
        this.valor = valor;
        this.unidad = unidad;
        this.estadoValidacion = estadoValidacion != null ? estadoValidacion : EstadoValidacion.PENDIENTE;
        this.observaciones = observaciones;
        this.fechaRegistro = fechaRegistro;
    }

    public static DatoFuenteBuilder builder() {
        return new DatoFuenteBuilder();
    }

    public static class DatoFuenteBuilder {
        private Long id;
        private FuenteInformacion fuente;
        private Usuario usuarioCarga;
        private String nombreArchivo;
        private LocalDateTime fechaDato;
        private String ubicacion;
        private String ubicacionNormalizada;
        private TipoActividad tipoActividad;
        private String indicador;
        private BigDecimal valor;
        private String unidad;
        private EstadoValidacion estadoValidacion = EstadoValidacion.PENDIENTE;
        private String observaciones;
        private LocalDateTime fechaRegistro;

        public DatoFuenteBuilder id(Long id) { this.id = id; return this; }
        public DatoFuenteBuilder fuente(FuenteInformacion fuente) { this.fuente = fuente; return this; }
        public DatoFuenteBuilder usuarioCarga(Usuario usuarioCarga) { this.usuarioCarga = usuarioCarga; return this; }
        public DatoFuenteBuilder nombreArchivo(String nombreArchivo) { this.nombreArchivo = nombreArchivo; return this; }
        public DatoFuenteBuilder fechaDato(LocalDateTime fechaDato) { this.fechaDato = fechaDato; return this; }
        public DatoFuenteBuilder ubicacion(String ubicacion) { this.ubicacion = ubicacion; return this; }
        public DatoFuenteBuilder ubicacionNormalizada(String ubicacionNormalizada) { this.ubicacionNormalizada = ubicacionNormalizada; return this; }
        public DatoFuenteBuilder tipoActividad(TipoActividad tipoActividad) { this.tipoActividad = tipoActividad; return this; }
        public DatoFuenteBuilder indicador(String indicador) { this.indicador = indicador; return this; }
        public DatoFuenteBuilder valor(BigDecimal valor) { this.valor = valor; return this; }
        public DatoFuenteBuilder unidad(String unidad) { this.unidad = unidad; return this; }
        public DatoFuenteBuilder estadoValidacion(EstadoValidacion estadoValidacion) { this.estadoValidacion = estadoValidacion; return this; }
        public DatoFuenteBuilder observaciones(String observaciones) { this.observaciones = observaciones; return this; }
        public DatoFuenteBuilder fechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; return this; }

        public DatoFuente build() {
            return new DatoFuente(id, fuente, usuarioCarga, nombreArchivo, fechaDato, ubicacion, 
                                  ubicacionNormalizada, tipoActividad, indicador, valor, unidad, 
                                  estadoValidacion, observaciones, fechaRegistro);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public FuenteInformacion getFuente() { return fuente; }
    public void setFuente(FuenteInformacion fuente) { this.fuente = fuente; }

    public Usuario getUsuarioCarga() { return usuarioCarga; }
    public void setUsuarioCarga(Usuario usuarioCarga) { this.usuarioCarga = usuarioCarga; }

    public String getNombreArchivo() { return nombreArchivo; }
    public void setNombreArchivo(String nombreArchivo) { this.nombreArchivo = nombreArchivo; }

    public LocalDateTime getFechaDato() { return fechaDato; }
    public void setFechaDato(LocalDateTime fechaDato) { this.fechaDato = fechaDato; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getUbicacionNormalizada() { return ubicacionNormalizada; }
    public void setUbicacionNormalizada(String ubicacionNormalizada) { this.ubicacionNormalizada = ubicacionNormalizada; }

    public TipoActividad getTipoActividad() { return tipoActividad; }
    public void setTipoActividad(TipoActividad tipoActividad) { this.tipoActividad = tipoActividad; }

    public String getIndicador() { return indicador; }
    public void setIndicador(String indicador) { this.indicador = indicador; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }

    public EstadoValidacion getEstadoValidacion() { return estadoValidacion; }
    public void setEstadoValidacion(EstadoValidacion estadoValidacion) { this.estadoValidacion = estadoValidacion; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatoFuente that = (DatoFuente) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DatoFuente{" +
                "id=" + id +
                ", ubicacion='" + ubicacion + '\'' +
                ", tipoActividad=" + tipoActividad +
                ", indicador='" + indicador + '\'' +
                ", valor=" + valor +
                ", unidad='" + unidad + '\'' +
                '}';
    }
}
