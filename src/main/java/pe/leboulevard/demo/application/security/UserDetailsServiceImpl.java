package pe.leboulevard.demo.application.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.leboulevard.demo.infrastructure.usuarios.entity.UsuariosEntity;
import pe.leboulevard.demo.infrastructure.usuarios.jpa.UsuariosRepositoryJpa;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuariosRepositoryJpa usuariosRepositoryJpa;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // --- LÍNEA CORREGIDA ---
        // Cambiamos findByUsername por findByUsernameIgnoreCase
        UsuariosEntity usuario = usuariosRepositoryJpa.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        Set<GrantedAuthority> authorities = new HashSet<>();

        // 1. Añadir el ROL como una autoridad
        authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre()));

        // 2. Añadir cada PERMISO como una autoridad
        if (usuario.getRol().getPermisos() != null) {
            usuario.getRol().getPermisos().forEach(permiso -> {
                authorities.add(new SimpleGrantedAuthority(permiso.getNombre()));
            });
        }

        return new User(
                usuario.getUsername(),
                usuario.getPasswordHash(),
                usuario.getEstaActivo(),
                true,
                true,
                true,
                authorities
        );
    }
}