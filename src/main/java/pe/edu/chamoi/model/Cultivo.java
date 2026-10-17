package pe.edu.chamoi.model;

import jakarta.persistence.*;
import pe.edu.chamoi.model.enums.EstadoGenerico;
import pe.edu.chamoi.model.enums.TipoCultivo;

import java.util.Objects;

@Entity
@Table(name = "cultivos")
public class Cultivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true, length = 120)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoCultivo tipo = TipoCultivo.OTRO;

    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoGenerico estado = EstadoGenerico.ACTIVO;

    public Cultivo() {
    }

    public Cultivo(Long id, String nombre, TipoCultivo tipo, String descripcion, EstadoGenerico estado) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo != null ? tipo : TipoCultivo.OTRO;
        this.descripcion = descripcion;
        this.estado = estado != null ? estado : EstadoGenerico.ACTIVO;
    }

    public static CultivoBuilder builder() {
        return new CultivoBuilder();
    }

    public static class CultivoBuilder {
        private Long id;
        private String nombre;
        private TipoCultivo tipo = TipoCultivo.OTRO;
        private String descripcion;
        private EstadoGenerico estado = EstadoGenerico.ACTIVO;

        public CultivoBuilder id(Long id) { this.id = id; return this; }
        public CultivoBuilder nombre(String nombre) { this.nombre = nombre; return this; }
        public CultivoBuilder tipo(TipoCultivo tipo) { this.tipo = tipo; return this; }
        public CultivoBuilder descripcion(String descripcion) { this.descripcion = descripcion; return this; }
        public CultivoBuilder estado(EstadoGenerico estado) { this.estado = estado; return this; }

        public Cultivo build() {
            return new Cultivo(id, nombre, tipo, descripcion, estado);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public TipoCultivo getTipo() { return tipo; }
    public void setTipo(TipoCultivo tipo) { this.tipo = tipo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public EstadoGenerico getEstado() { return estado; }
    public void setEstado(EstadoGenerico estado) { this.estado = estado; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cultivo cultivo = (Cultivo) o;
        return Objects.equals(id, cultivo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cultivo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo=" + tipo +
                ", estado=" + estado +
                '}';
    }
}
