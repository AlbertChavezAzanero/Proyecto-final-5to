package pe.leboulevard.demo.infrastructure.usuarios.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pe.leboulevard.demo.domain.usuarios.model.UsuariosModel;
import pe.leboulevard.demo.domain.usuarios.repository.UsuariosRepository;
import pe.leboulevard.demo.infrastructure.usuarios.jpa.UsuariosRepositoryJpa;
import pe.leboulevard.demo.infrastructure.usuarios.mapper.UsuariosMapper;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsuariosRepositoryImpl implements UsuariosRepository {

    private final UsuariosRepositoryJpa usuariosRepositoryJpa;
    private final UsuariosMapper usuariosMapper;

    @Override
    public Optional<UsuariosModel> findByUsername(String username) {
        return usuariosRepositoryJpa.findByUsername(username)
                .map(usuariosMapper::toModel);
    }
}