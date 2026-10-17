package pe.edu.chamoi.model;

import jakarta.persistence.*;
import pe.edu.chamoi.model.enums.TipoMensaje;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "mensajes_chat")
public class MensajeChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_mensaje", nullable = false)
    private TipoMensaje tipoMensaje;

    @Column(name = "contenido", nullable = false, columnDefinition = "text")
    private String contenido;

    @Column(name = "modelo_ia", length = 100)
    private String modeloIa;

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

    public MensajeChat() {
    }

    public MensajeChat(Long id, Usuario usuario, TipoMensaje tipoMensaje, String contenido, 
                       String modeloIa, LocalDateTime fechaEnvio) {
        this.id = id;
        this.usuario = usuario;
        this.tipoMensaje = tipoMensaje;
        this.contenido = contenido;
        this.modeloIa = modeloIa;
        this.fechaEnvio = fechaEnvio;
    }

    public static MensajeChatBuilder builder() {
        return new MensajeChatBuilder();
    }

    public static class MensajeChatBuilder {
        private Long id;
        private Usuario usuario;
        private TipoMensaje tipoMensaje;
        private String contenido;
        private String modeloIa;
        private LocalDateTime fechaEnvio;

        public MensajeChatBuilder id(Long id) { this.id = id; return this; }
        public MensajeChatBuilder usuario(Usuario usuario) { this.usuario = usuario; return this; }
        public MensajeChatBuilder tipoMensaje(TipoMensaje tipoMensaje) { this.tipoMensaje = tipoMensaje; return this; }
        public MensajeChatBuilder contenido(String contenido) { this.contenido = contenido; return this; }
        public MensajeChatBuilder modeloIa(String modeloIa) { this.modeloIa = modeloIa; return this; }
        public MensajeChatBuilder fechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; return this; }

        public MensajeChat build() {
            return new MensajeChat(id, usuario, tipoMensaje, contenido, modeloIa, fechaEnvio);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public TipoMensaje getTipoMensaje() { return tipoMensaje; }
    public void setTipoMensaje(TipoMensaje tipoMensaje) { this.tipoMensaje = tipoMensaje; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public String getModeloIa() { return modeloIa; }
    public void setModeloIa(String modeloIa) { this.modeloIa = modeloIa; }

    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MensajeChat that = (MensajeChat) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MensajeChat{" +
                "id=" + id +
                ", tipoMensaje=" + tipoMensaje +
                ", modeloIa='" + modeloIa + '\'' +
                ", fechaEnvio=" + fechaEnvio +
                '}';
    }
}
