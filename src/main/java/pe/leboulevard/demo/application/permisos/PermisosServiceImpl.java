package pe.leboulevard.demo.application.permisos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.leboulevard.demo.domain.permisos.model.PermisosModel;
import pe.leboulevard.demo.domain.permisos.service.PermisosService;
import pe.leboulevard.demo.domain.roles.model.RolesModel;
import pe.leboulevard.demo.infrastructure.permisos.entity.PermisosEntity;
import pe.leboulevard.demo.infrastructure.permisos.jpa.PermisosRepositoryJpa;
import pe.leboulevard.demo.infrastructure.permisos.mapper.PermisosMapper;
import pe.leboulevard.demo.infrastructure.roles.entity.RolesEntity;
import pe.leboulevard.demo.infrastructure.roles.jpa.RolesRepositoryJpa;
import pe.leboulevard.demo.infrastructure.roles.mapper.RolesMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermisosServiceImpl implements PermisosService {

    private final RolesRepositoryJpa rolesRepositoryJpa;
    private final PermisosRepositoryJpa permisosRepositoryJpa;
    private final RolesMapper rolesMapper;
    private final PermisosMapper permisosMapper;

    @Override
    public List<RolesModel> listarTodosLosRoles() {
        return rolesRepositoryJpa.findAll().stream().map(rolesMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public List<PermisosModel> listarTodosLosPermisos() {
        return permisosRepositoryJpa.findAll().stream().map(permisosMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Optional<RolesModel> buscarRolPorId(Integer idRol) {
        return rolesRepositoryJpa.findById(idRol.longValue()).map(rolesMapper::toModel);
    }

    @Override
    @Transactional
    public void actualizarPermisosDeRol(Integer idRol, List<Integer> permisosIds) {
        RolesEntity rol = rolesRepositoryJpa.findById(idRol.longValue())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        if (permisosIds == null || permisosIds.isEmpty()) {
            rol.getPermisos().clear();
        } else {
            // Convertimos la lista de Integer a una lista de Long
            List<Long> permisosIdsAsLong = permisosIds.stream()
                    .map(Long::valueOf)
                    .collect(Collectors.toList());

            // Buscamos las entidades de los permisos seleccionados usando la lista de Longs
            Set<PermisosEntity> nuevosPermisos = new HashSet<>(permisosRepositoryJpa.findAllById(permisosIdsAsLong));
            rol.setPermisos(nuevosPermisos);
        }

        rolesRepositoryJpa.save(rol);
    }
}