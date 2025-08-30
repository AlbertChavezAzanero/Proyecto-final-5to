package pe.leboulevard.demo.domain.permisos.service;

import pe.leboulevard.demo.domain.permisos.model.PermisosModel;
import pe.leboulevard.demo.domain.roles.model.RolesModel;

import java.util.List;
import java.util.Optional;

public interface PermisosService {
    List<RolesModel> listarTodosLosRoles();
    List<PermisosModel> listarTodosLosPermisos();
    Optional<RolesModel> buscarRolPorId(Integer idRol);
    void actualizarPermisosDeRol(Integer idRol, List<Integer> permisosIds);
}