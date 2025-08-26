package pe.leboulevard.demo.application.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pe.leboulevard.demo.domain.usuarios.model.UsuariosModel;

import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final UsuariosModel user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Asignamos el rol del usuario como una autoridad
        return List.of(new SimpleGrantedAuthority(user.getRol().getNombre()));
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // Los siguientes métodos se pueden dejar como true por ahora
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
        return user.getEstaActivo();
    }
}
