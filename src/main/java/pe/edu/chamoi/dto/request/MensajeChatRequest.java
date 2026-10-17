package pe.edu.chamoi.dto.request;

import jakarta.validation.constraints.NotBlank;

public class MensajeChatRequest {

    @NotBlank(message = "El contenido del mensaje no puede estar vacío")
    private String contenido;

    public MensajeChatRequest() {
    }

    public MensajeChatRequest(String contenido) {
        this.contenido = contenido;
    }

    public static MensajeChatRequestBuilder builder() {
        return new MensajeChatRequestBuilder();
    }

    public static class MensajeChatRequestBuilder {
        private String contenido;

        public MensajeChatRequestBuilder contenido(String contenido) { this.contenido = contenido; return this; }
        public MensajeChatRequest build() { return new MensajeChatRequest(contenido); }
    }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
}
