package pe.edu.chamoi.dto.response;

import pe.edu.chamoi.model.enums.TipoMensaje;
import java.time.LocalDateTime;

public class MensajeChatResponse {

    private Long id;
    private TipoMensaje tipoMensaje;
    private String contenido;
    private String modeloIa;
    private LocalDateTime fechaEnvio;

    public MensajeChatResponse() {
    }

    public MensajeChatResponse(Long id, TipoMensaje tipoMensaje, String contenido, String modeloIa, LocalDateTime fechaEnvio) {
        this.id = id;
        this.tipoMensaje = tipoMensaje;
        this.contenido = contenido;
        this.modeloIa = modeloIa;
        this.fechaEnvio = fechaEnvio;
    }

    public static MensajeChatResponseBuilder builder() {
        return new MensajeChatResponseBuilder();
    }

    public static class MensajeChatResponseBuilder {
        private Long id;
        private TipoMensaje tipoMensaje;
        private String contenido;
        private String modeloIa;
        private LocalDateTime fechaEnvio;

        public MensajeChatResponseBuilder id(Long id) { this.id = id; return this; }
        public MensajeChatResponseBuilder tipoMensaje(TipoMensaje tipoMensaje) { this.tipoMensaje = tipoMensaje; return this; }
        public MensajeChatResponseBuilder contenido(String contenido) { this.contenido = contenido; return this; }
        public MensajeChatResponseBuilder modeloIa(String modeloIa) { this.modeloIa = modeloIa; return this; }
        public MensajeChatResponseBuilder fechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; return this; }

        public MensajeChatResponse build() {
            return new MensajeChatResponse(id, tipoMensaje, contenido, modeloIa, fechaEnvio);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TipoMensaje getTipoMensaje() { return tipoMensaje; }
    public void setTipoMensaje(TipoMensaje tipoMensaje) { this.tipoMensaje = tipoMensaje; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public String getModeloIa() { return modeloIa; }
    public void setModeloIa(String modeloIa) { this.modeloIa = modeloIa; }

    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }
}
