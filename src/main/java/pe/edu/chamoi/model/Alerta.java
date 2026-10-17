package pe.edu.chamoi.model;

import jakarta.persistence.*;
import pe.edu.chamoi.model.enums.NivelAlerta;
import pe.edu.chamoi.model.enums.TipoAlerta;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "alertas")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoAlerta tipo;

    @Column(name = "titulo", nullable = false, length = 180)
    private String titulo;

    @Column(name = "mensaje", nullable = false, columnDefinition = "text")
    private String mensaje;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel")
    private NivelAlerta nivel = NivelAlerta.INFO;

    @Column(name = "leida")
    private Boolean leida = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analisis_id")
    private Analisis analisis;

    @Column(name = "fecha_evento")
    private LocalDateTime fechaEvento;

    public Alerta() {
    }

    public Alerta(Long id, Usuario usuario, TipoAlerta tipo, String titulo, String mensaje, 
                  NivelAlerta nivel, Boolean leida, Analisis analisis, LocalDateTime fechaEvento) {
        this.id = id;
        this.usuario = usuario;
        this.tipo = tipo;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.nivel = nivel != null ? nivel : NivelAlerta.INFO;
        this.leida = leida != null ? leida : false;
        this.analisis = analisis;
        this.fechaEvento = fechaEvento;
    }

    public static AlertaBuilder builder() {
        return new AlertaBuilder();
    }

    public static class AlertaBuilder {
        private Long id;
        private Usuario usuario;
        private TipoAlerta tipo;
        private String titulo;
        private String mensaje;
        private NivelAlerta nivel = NivelAlerta.INFO;
        private Boolean leida = false;
        private Analisis analisis;
        private LocalDateTime fechaEvento;

        public AlertaBuilder id(Long id) { this.id = id; return this; }
        public AlertaBuilder usuario(Usuario usuario) { this.usuario = usuario; return this; }
        public AlertaBuilder tipo(TipoAlerta tipo) { this.tipo = tipo; return this; }
        public AlertaBuilder titulo(String titulo) { this.titulo = titulo; return this; }
        public AlertaBuilder mensaje(String mensaje) { this.mensaje = mensaje; return this; }
        public AlertaBuilder nivel(NivelAlerta nivel) { this.nivel = nivel; return this; }
        public AlertaBuilder leida(Boolean leida) { this.leida = leida; return this; }
        public AlertaBuilder analisis(Analisis analisis) { this.analisis = analisis; return this; }
        public AlertaBuilder fechaEvento(LocalDateTime fechaEvento) { this.fechaEvento = fechaEvento; return this; }

        public Alerta build() {
            return new Alerta(id, usuario, tipo, titulo, mensaje, nivel, leida, analisis, fechaEvento);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public TipoAlerta getTipo() { return tipo; }
    public void setTipo(TipoAlerta tipo) { this.tipo = tipo; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public NivelAlerta getNivel() { return nivel; }
    public void setNivel(NivelAlerta nivel) { this.nivel = nivel; }

    public Boolean getLeida() { return leida; }
    public void setLeida(Boolean leida) { this.leida = leida; }

    public Analisis getAnalisis() { return analisis; }
    public void setAnalisis(Analisis analisis) { this.analisis = analisis; }

    public LocalDateTime getFechaEvento() { return fechaEvento; }
    public void setFechaEvento(LocalDateTime fechaEvento) { this.fechaEvento = fechaEvento; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alerta alerta = (Alerta) o;
        return Objects.equals(id, alerta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Alerta{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", titulo='" + titulo + '\'' +
                ", nivel=" + nivel +
                ", leida=" + leida +
                ", fechaEvento=" + fechaEvento +
                '}';
    }
}
