package pe.edu.chamoi.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pe.edu.chamoi.model.Usuario;
import pe.edu.chamoi.model.enums.EstadoUsuario;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final Usuario usuario;
    private final String rolNombre;
    private final String nombreCompleto;
    private final List<GrantedAuthority> authorities;

    public CustomUserDetails(Usuario usuario) {
        this.usuario = usuario;
        if (usuario != null && usuario.getRol() != null && usuario.getRol().getNombre() != null) {
            this.rolNombre = usuario.getRol().getNombre();
            this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + this.rolNombre));
        } else {
            this.rolNombre = "";
            this.authorities = List.of();
        }
        if (usuario != null) {
            String n = usuario.getNombres() != null ? usuario.getNombres() : "";
            String a = usuario.getApellidos() != null ? usuario.getApellidos() : "";
            this.nombreCompleto = (n + " " + a).trim();
        } else {
            this.nombreCompleto = "";
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() { 
        return usuario != null ? usuario.getPasswordHash() : null; 
    }

    @Override
    public String getUsername() { 
        return usuario != null ? usuario.getCorreo() : null; 
    }

    @Override
    public boolean isAccountNonExpired() { 
        return true; 
    }

    @Override
    public boolean isAccountNonLocked() { 
        return true; 
    }

    @Override
    public boolean isCredentialsNonExpired() { 
        return true; 
    }

    @Override
    public boolean isEnabled() { 
        return usuario != null && usuario.getEstado() == EstadoUsuario.ACTIVO; 
    }

    public Usuario getUsuario() { 
        return usuario; 
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getRolNombre() {
        return rolNombre;
    }
}
