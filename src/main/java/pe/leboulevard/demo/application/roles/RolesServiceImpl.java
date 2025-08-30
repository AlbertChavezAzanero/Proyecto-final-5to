package pe.leboulevard.demo.application.roles;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.leboulevard.demo.domain.roles.model.RolesModel;
import pe.leboulevard.demo.domain.roles.service.RolesService;
import pe.leboulevard.demo.infrastructure.roles.entity.RolesEntity;
import pe.leboulevard.demo.infrastructure.roles.jpa.RolesRepositoryJpa;
import pe.leboulevard.demo.infrastructure.roles.mapper.RolesMapper;
import pe.leboulevard.demo.infrastructure.usuarios.jpa.UsuariosRepositoryJpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {

    private final RolesRepositoryJpa rolesRepositoryJpa;
    private final UsuariosRepositoryJpa usuariosRepositoryJpa;
    private final RolesMapper rolesMapper;

    @Override
    public List<RolesModel> listarTodos() {
        return rolesRepositoryJpa.findAll().stream().map(rolesMapper::toModel).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RolesModel guardar(RolesModel rol) {
        // --- INICIO DE LA CORRECCIÓN ---
        if (rol.getIdRol() != null) {
            // --- LÓGICA DE ACTUALIZACIÓN ---
            RolesEntity entidadExistente = rolesRepositoryJpa.findById(rol.getIdRol().longValue())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + rol.getIdRol()));

            // Actualizamos solo los campos que vienen del formulario
            entidadExistente.setNombre(rol.getNombre());
            entidadExistente.setDescripcion(rol.getDescripcion());
            entidadExistente.setUsuarioActualizacion("admin-web-update"); // Asignamos usuario de actualización

            RolesEntity rolActualizado = rolesRepositoryJpa.save(entidadExistente);
            return rolesMapper.toModel(rolActualizado);

        } else {
            // --- LÓGICA DE CREACIÓN (la que ya tenías) ---
            rol.setUsuarioCreacion("admin-web-create");
            rol.setUsuarioActualizacion("admin-web-create");
            rol.setActivo(true); // Aseguramos que los nuevos roles estén activos

            RolesEntity entity = rolesMapper.toEntity(rol);
            RolesEntity savedEntity = rolesRepositoryJpa.save(entity);
            return rolesMapper.toModel(savedEntity);
        }
        // --- FIN DE LA CORRECCIÓN ---
    }

    @Override
    public Optional<RolesModel> buscarPorId(Integer id) {
        return rolesRepositoryJpa.findById(id.longValue()).map(rolesMapper::toModel);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) throws Exception {
        if (estaEnUso(id)) {
            throw new Exception("No se puede eliminar el rol porque está asignado a uno o más usuarios.");
        }
        rolesRepositoryJpa.deleteById(id.longValue());
    }

    @Override
    public boolean estaEnUso(Integer idRol) {
        return usuariosRepositoryJpa.existsByRolIdRol(idRol);
    }
}