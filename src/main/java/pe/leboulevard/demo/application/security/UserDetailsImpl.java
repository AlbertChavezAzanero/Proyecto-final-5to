package pe.leboulevard.demo.application.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pe.leboulevard.demo.domain.usuarios.model.UsuariosModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public record UserDetailsImpl(UsuariosModel user) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Añade el ROL (ej: "ROLE_Administrador")
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user().getRol().getNombre()));

        // Añade todos los PERMISOS del rol (ej: "GESTIONAR_EMPLEADOS")
        if (user().getRol().getPermisos() != null) {
            user().getRol().getPermisos().forEach(permiso ->
                    authorities.add(new SimpleGrantedAuthority(permiso.getNombre()))
            );
        }

        return authorities;
    }

    @Override
    public String getPassword() { return user().getPasswordHash(); }
    @Override
    public String getUsername() { return user().getUsername(); }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return user().getEstaActivo(); }
}