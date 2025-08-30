package pe.leboulevard.demo.application.usuarios;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.leboulevard.demo.domain.usuarios.model.UsuariosModel;
import pe.leboulevard.demo.domain.usuarios.service.UsuariosService;
import pe.leboulevard.demo.infrastructure.roles.entity.RolesEntity;
import pe.leboulevard.demo.infrastructure.roles.jpa.RolesRepositoryJpa;
import pe.leboulevard.demo.infrastructure.usuarios.entity.UsuariosEntity;
import pe.leboulevard.demo.infrastructure.usuarios.jpa.UsuariosRepositoryJpa;
import pe.leboulevard.demo.infrastructure.usuarios.mapper.UsuariosMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuariosServiceImpl implements UsuariosService {

    private final UsuariosRepositoryJpa usuariosRepositoryJpa;
    private final RolesRepositoryJpa rolesRepositoryJpa; // Inyección añadida
    private final UsuariosMapper usuariosMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuariosModel guardarUsuario(UsuariosModel usuarioModel) {
        UsuariosEntity usuariosEntity = usuariosMapper.toEntity(usuarioModel);
        UsuariosEntity usuarioGuardado = usuariosRepositoryJpa.save(usuariosEntity);
        return usuariosMapper.toModel(usuarioGuardado);
    }

    @Override
    public List<UsuariosModel> listarTodos() {
        return usuariosRepositoryJpa.findAll()
                .stream()
                .map(usuariosMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UsuariosModel> buscarPorId(Integer id) {
        return usuariosRepositoryJpa.findById(id).map(usuariosMapper::toModel);
    }

    @Override
    @Transactional
    public UsuariosModel crearUsuarioDesdeAdmin(UsuariosModel usuario) {
        String passwordHasheado = passwordEncoder.encode(usuario.getPasswordHash());
        usuario.setPasswordHash(passwordHasheado);

        usuario.setUsuarioCreacion("admin-panel");
        usuario.setUsuarioActualizacion("admin-panel");

        UsuariosEntity nuevoUsuarioEntity = usuariosMapper.toEntity(usuario);
        UsuariosEntity usuarioGuardado = usuariosRepositoryJpa.save(nuevoUsuarioEntity);

        return usuariosMapper.toModel(usuarioGuardado);
    }

    @Override
    @Transactional
    public UsuariosModel actualizarUsuarioDesdeAdmin(UsuariosModel usuarioModel) {
        // 1. Buscamos el usuario existente en la base de datos
        UsuariosEntity entidadExistente = usuariosRepositoryJpa.findById(usuarioModel.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioModel.getIdUsuario()));

        // 2. Actualizamos los campos que se pueden modificar
        entidadExistente.setEmail(usuarioModel.getEmail());
        entidadExistente.setEstaActivo(usuarioModel.getEstaActivo());

        // 3. Lógica corregida para actualizar el rol
        if (!entidadExistente.getRol().getIdRol().equals(usuarioModel.getRol().getIdRol())) {
            // Buscamos la nueva entidad Rol en la base de datos
            RolesEntity nuevoRol = rolesRepositoryJpa.findById(usuarioModel.getRol().getIdRol().longValue())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + usuarioModel.getRol().getIdRol()));
            // Asignamos la entidad Rol completa al usuario
            entidadExistente.setRol(nuevoRol);
        }

        // 4. Opcional: Cambiar la contraseña solo si se ha proporcionado una nueva
        if (usuarioModel.getPasswordHash() != null && !usuarioModel.getPasswordHash().isEmpty()) {
            entidadExistente.setPasswordHash(passwordEncoder.encode(usuarioModel.getPasswordHash()));
        }

        entidadExistente.setUsuarioActualizacion("admin-panel-update");

        // 5. Guardamos los cambios
        UsuariosEntity usuarioActualizado = usuariosRepositoryJpa.save(entidadExistente);
        return usuariosMapper.toModel(usuarioActualizado);
    }
}