package pe.edu.chamoi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistroRequest {

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(max = 100, message = "Los nombres no deben exceder 100 caracteres")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 100, message = "Los apellidos no deben exceder 100 caracteres")
    private String apellidos;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Formato de correo inválido")
    @Size(max = 150, message = "El correo no debe exceder 150 caracteres")
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 100, message = "La contraseña debe tener entre 6 y 100 caracteres")
    private String password;

    @Size(max = 20, message = "El teléfono no debe exceder 20 caracteres")
    private String telefono;

    @NotBlank(message = "El rol es obligatorio")
    private String rolNombre = "AGRICULTOR";

    public RegistroRequest() {
    }

    public RegistroRequest(String nombres, String apellidos, String correo, String password, String telefono, String rolNombre) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.password = password;
        this.telefono = telefono;
        this.rolNombre = rolNombre != null ? rolNombre : "AGRICULTOR";
    }

    public static RegistroRequestBuilder builder() {
        return new RegistroRequestBuilder();
    }

    public static class RegistroRequestBuilder {
        private String nombres;
        private String apellidos;
        private String correo;
        private String password;
        private String telefono;
        private String rolNombre = "AGRICULTOR";

        public RegistroRequestBuilder nombres(String nombres) { this.nombres = nombres; return this; }
        public RegistroRequestBuilder apellidos(String apellidos) { this.apellidos = apellidos; return this; }
        public RegistroRequestBuilder correo(String correo) { this.correo = correo; return this; }
        public RegistroRequestBuilder password(String password) { this.password = password; return this; }
        public RegistroRequestBuilder telefono(String telefono) { this.telefono = telefono; return this; }
        public RegistroRequestBuilder rolNombre(String rolNombre) { this.rolNombre = rolNombre; return this; }

        public RegistroRequest build() {
            return new RegistroRequest(nombres, apellidos, correo, password, telefono, rolNombre);
        }
    }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getRolNombre() { return rolNombre; }
    public void setRolNombre(String rolNombre) { this.rolNombre = rolNombre; }

    @Override
    public String toString() {
        return "RegistroRequest{" +
                "nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", rolNombre='" + rolNombre + '\'' +
                '}';
    }
}
