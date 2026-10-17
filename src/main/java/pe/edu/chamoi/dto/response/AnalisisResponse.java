package pe.edu.chamoi.dto.response;

import pe.edu.chamoi.model.enums.EstadoAnalisis;
import pe.edu.chamoi.model.enums.TipoActividad;
import java.time.LocalDateTime;

public class AnalisisResponse {

    private Long id;
    private TipoActividad tipoActividad;
    private EstadoAnalisis estado;
    private String resumenSolicitud;
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaFinalizacion;

    public AnalisisResponse() {
    }

    public AnalisisResponse(Long id, TipoActividad tipoActividad, EstadoAnalisis estado, 
                            String resumenSolicitud, LocalDateTime fechaSolicitud, 
                            LocalDateTime fechaFinalizacion) {
        this.id = id;
        this.tipoActividad = tipoActividad;
        this.estado = estado;
        this.resumenSolicitud = resumenSolicitud;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public static AnalisisResponseBuilder builder() {
        return new AnalisisResponseBuilder();
    }

    public static class AnalisisResponseBuilder {
        private Long id;
        private TipoActividad tipoActividad;
        private EstadoAnalisis estado;
        private String resumenSolicitud;
        private LocalDateTime fechaSolicitud;
        private LocalDateTime fechaFinalizacion;

        public AnalisisResponseBuilder id(Long id) { this.id = id; return this; }
        public AnalisisResponseBuilder tipoActividad(TipoActividad tipoActividad) { this.tipoActividad = tipoActividad; return this; }
        public AnalisisResponseBuilder estado(EstadoAnalisis estado) { this.estado = estado; return this; }
        public AnalisisResponseBuilder resumenSolicitud(String resumenSolicitud) { this.resumenSolicitud = resumenSolicitud; return this; }
        public AnalisisResponseBuilder fechaSolicitud(LocalDateTime fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; return this; }
        public AnalisisResponseBuilder fechaFinalizacion(LocalDateTime fechaFinalizacion) { this.fechaFinalizacion = fechaFinalizacion; return this; }

        public AnalisisResponse build() {
            return new AnalisisResponse(id, tipoActividad, estado, resumenSolicitud, fechaSolicitud, fechaFinalizacion);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TipoActividad getTipoActividad() { return tipoActividad; }
    public void setTipoActividad(TipoActividad tipoActividad) { this.tipoActividad = tipoActividad; }

    public EstadoAnalisis getEstado() { return estado; }
    public void setEstado(EstadoAnalisis estado) { this.estado = estado; }

    public String getResumenSolicitud() { return resumenSolicitud; }
    public void setResumenSolicitud(String resumenSolicitud) { this.resumenSolicitud = resumenSolicitud; }

    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDateTime fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public LocalDateTime getFechaFinalizacion() { return fechaFinalizacion; }
    public void setFechaFinalizacion(LocalDateTime fechaFinalizacion) { this.fechaFinalizacion = fechaFinalizacion; }
}
