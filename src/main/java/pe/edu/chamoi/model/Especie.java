package pe.edu.chamoi.model;

import jakarta.persistence.*;
import pe.edu.chamoi.model.enums.EstadoGenerico;
import pe.edu.chamoi.model.enums.TipoEspecie;

import java.util.Objects;

@Entity
@Table(name = "especies")
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true, length = 120)
    private String nombre;

    @Column(name = "nombre_cientifico", length = 150)
    private String nombreCientifico;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoEspecie tipo = TipoEspecie.OTRO;

    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoGenerico estado = EstadoGenerico.ACTIVO;

    public Especie() {
    }

    public Especie(Long id, String nombre, String nombreCientifico, TipoEspecie tipo, String descripcion, EstadoGenerico estado) {
        this.id = id;
        this.nombre = nombre;
        this.nombreCientifico = nombreCientifico;
        this.tipo = tipo != null ? tipo : TipoEspecie.OTRO;
        this.descripcion = descripcion;
        this.estado = estado != null ? estado : EstadoGenerico.ACTIVO;
    }

    public static EspecieBuilder builder() {
        return new EspecieBuilder();
    }

    public static class EspecieBuilder {
        private Long id;
        private String nombre;
        private String nombreCientifico;
        private TipoEspecie tipo = TipoEspecie.OTRO;
        private String descripcion;
        private EstadoGenerico estado = EstadoGenerico.ACTIVO;

        public EspecieBuilder id(Long id) { this.id = id; return this; }
        public EspecieBuilder nombre(String nombre) { this.nombre = nombre; return this; }
        public EspecieBuilder nombreCientifico(String nombreCientifico) { this.nombreCientifico = nombreCientifico; return this; }
        public EspecieBuilder tipo(TipoEspecie tipo) { this.tipo = tipo; return this; }
        public EspecieBuilder descripcion(String descripcion) { this.descripcion = descripcion; return this; }
        public EspecieBuilder estado(EstadoGenerico estado) { this.estado = estado; return this; }

        public Especie build() {
            return new Especie(id, nombre, nombreCientifico, tipo, descripcion, estado);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNombreCientifico() { return nombreCientifico; }
    public void setNombreCientifico(String nombreCientifico) { this.nombreCientifico = nombreCientifico; }

    public TipoEspecie getTipo() { return tipo; }
    public void setTipo(TipoEspecie tipo) { this.tipo = tipo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public EstadoGenerico getEstado() { return estado; }
    public void setEstado(EstadoGenerico estado) { this.estado = estado; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Especie especie = (Especie) o;
        return Objects.equals(id, especie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Especie{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nombreCientifico='" + nombreCientifico + '\'' +
                ", tipo=" + tipo +
                ", estado=" + estado +
                '}';
    }
}
