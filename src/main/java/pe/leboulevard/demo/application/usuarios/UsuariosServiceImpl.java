package pe.leboulevard.demo.application.usuarios;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.leboulevard.demo.domain.usuarios.model.UsuariosModel;
import pe.leboulevard.demo.domain.usuarios.service.UsuariosService;
import pe.leboulevard.demo.infrastructure.usuarios.entity.UsuariosEntity;
import pe.leboulevard.demo.infrastructure.usuarios.jpa.UsuariosRepositoryJpa;
import pe.leboulevard.demo.infrastructure.usuarios.mapper.UsuariosMapper;

@Service
@RequiredArgsConstructor
public class UsuariosServiceImpl implements UsuariosService {

    private final UsuariosRepositoryJpa usuariosRepositoryJpa;
    private final UsuariosMapper usuariosMapper;

    @Override
    public UsuariosModel guardarUsuario(UsuariosModel usuarioModel) {
        // 1. Convertimos el Model a una Entity para la base de datos.
        UsuariosEntity usuariosEntity = usuariosMapper.toEntity(usuarioModel);

        // 2. Guardamos la Entity en la base de datos.
        UsuariosEntity usuarioGuardado = usuariosRepositoryJpa.save(usuariosEntity);

        // 3. Convertimos la Entity guardada de nuevo a un Model para devolverla.
        return usuariosMapper.toModel(usuarioGuardado);
    }
}