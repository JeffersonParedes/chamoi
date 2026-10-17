package pe.edu.chamoi.model;

import jakarta.persistence.*;
import pe.edu.chamoi.model.enums.EstadoAnalisis;
import pe.edu.chamoi.model.enums.TipoActividad;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "analisis")
public class Analisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_actividad", nullable = false)
    private TipoActividad tipoActividad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "situacion_agricola_id")
    private SituacionAgricola situacionAgricola;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "situacion_pesquera_id")
    private SituacionPesquera situacionPesquera;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoAnalisis estado = EstadoAnalisis.SOLICITADO;

    @Column(name = "resumen_solicitud", columnDefinition = "text")
    private String resumenSolicitud;

    @Column(name = "fecha_solicitud")
    private LocalDateTime fechaSolicitud;

    @Column(name = "fecha_finalizacion")
    private LocalDateTime fechaFinalizacion;

    @Column(name = "mensaje_error", columnDefinition = "text")
    private String mensajeError;

    @OneToOne(mappedBy = "analisis", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ResultadoAnalisis resultadoAnalisis;

    @OneToMany(mappedBy = "analisis")
    private List<Alerta> alertas = new ArrayList<>();

    public Analisis() {
    }

    public Analisis(Long id, Usuario usuario, TipoActividad tipoActividad, 
                    SituacionAgricola situacionAgricola, SituacionPesquera situacionPesquera, 
                    EstadoAnalisis estado, String resumenSolicitud, LocalDateTime fechaSolicitud, 
                    LocalDateTime fechaFinalizacion, String mensajeError) {
        this.id = id;
        this.usuario = usuario;
        this.tipoActividad = tipoActividad;
        this.situacionAgricola = situacionAgricola;
        this.situacionPesquera = situacionPesquera;
        this.estado = estado != null ? estado : EstadoAnalisis.SOLICITADO;
        this.resumenSolicitud = resumenSolicitud;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaFinalizacion = fechaFinalizacion;
        this.mensajeError = mensajeError;
    }

    public static AnalisisBuilder builder() {
        return new AnalisisBuilder();
    }

    public static class AnalisisBuilder {
        private Long id;
        private Usuario usuario;
        private TipoActividad tipoActividad;
        private SituacionAgricola situacionAgricola;
        private SituacionPesquera situacionPesquera;
        private EstadoAnalisis estado = EstadoAnalisis.SOLICITADO;
        private String resumenSolicitud;
        private LocalDateTime fechaSolicitud;
        private LocalDateTime fechaFinalizacion;
        private String mensajeError;

        public AnalisisBuilder id(Long id) { this.id = id; return this; }
        public AnalisisBuilder usuario(Usuario usuario) { this.usuario = usuario; return this; }
        public AnalisisBuilder tipoActividad(TipoActividad tipoActividad) { this.tipoActividad = tipoActividad; return this; }
        public AnalisisBuilder situacionAgricola(SituacionAgricola situacionAgricola) { this.situacionAgricola = situacionAgricola; return this; }
        public AnalisisBuilder situacionPesquera(SituacionPesquera situacionPesquera) { this.situacionPesquera = situacionPesquera; return this; }
        public AnalisisBuilder estado(EstadoAnalisis estado) { this.estado = estado; return this; }
        public AnalisisBuilder resumenSolicitud(String resumenSolicitud) { this.resumenSolicitud = resumenSolicitud; return this; }
        public AnalisisBuilder fechaSolicitud(LocalDateTime fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; return this; }
        public AnalisisBuilder fechaFinalizacion(LocalDateTime fechaFinalizacion) { this.fechaFinalizacion = fechaFinalizacion; return this; }
        public AnalisisBuilder mensajeError(String mensajeError) { this.mensajeError = mensajeError; return this; }

        public Analisis build() {
            return new Analisis(id, usuario, tipoActividad, situacionAgricola, situacionPesquera, 
                                estado, resumenSolicitud, fechaSolicitud, fechaFinalizacion, mensajeError);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public TipoActividad getTipoActividad() { return tipoActividad; }
    public void setTipoActividad(TipoActividad tipoActividad) { this.tipoActividad = tipoActividad; }

    public SituacionAgricola getSituacionAgricola() { return situacionAgricola; }
    public void setSituacionAgricola(SituacionAgricola situacionAgricola) { this.situacionAgricola = situacionAgricola; }

    public SituacionPesquera getSituacionPesquera() { return situacionPesquera; }
    public void setSituacionPesquera(SituacionPesquera situacionPesquera) { this.situacionPesquera = situacionPesquera; }

    public EstadoAnalisis getEstado() { return estado; }
    public void setEstado(EstadoAnalisis estado) { this.estado = estado; }

    public String getResumenSolicitud() { return resumenSolicitud; }
    public void setResumenSolicitud(String resumenSolicitud) { this.resumenSolicitud = resumenSolicitud; }

    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDateTime fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public LocalDateTime getFechaFinalizacion() { return fechaFinalizacion; }
    public void setFechaFinalizacion(LocalDateTime fechaFinalizacion) { this.fechaFinalizacion = fechaFinalizacion; }

    public String getMensajeError() { return mensajeError; }
    public void setMensajeError(String mensajeError) { this.mensajeError = mensajeError; }

    public ResultadoAnalisis getResultadoAnalisis() { return resultadoAnalisis; }
    public void setResultadoAnalisis(ResultadoAnalisis resultadoAnalisis) { this.resultadoAnalisis = resultadoAnalisis; }

    public List<Alerta> getAlertas() { return alertas; }
    public void setAlertas(List<Alerta> alertas) { this.alertas = alertas; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Analisis analisis = (Analisis) o;
        return Objects.equals(id, analisis.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Analisis{" +
                "id=" + id +
                ", tipoActividad=" + tipoActividad +
                ", estado=" + estado +
                ", fechaSolicitud=" + fechaSolicitud +
                ", fechaFinalizacion=" + fechaFinalizacion +
                '}';
    }
}
