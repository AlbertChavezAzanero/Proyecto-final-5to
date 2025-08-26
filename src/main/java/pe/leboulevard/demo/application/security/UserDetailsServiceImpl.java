package pe.leboulevard.demo.application.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.leboulevard.demo.infrastructure.usuarios.entity.UsuariosEntity;
import pe.leboulevard.demo.infrastructure.usuarios.jpa.UsuariosRepositoryJpa;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuariosRepositoryJpa usuariosRepositoryJpa;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuariosEntity userEntity = usuariosRepositoryJpa.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return new User(
                userEntity.getUsername(),
                userEntity.getPasswordHash(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userEntity.getRol().getNombre()))
        );
    }
}