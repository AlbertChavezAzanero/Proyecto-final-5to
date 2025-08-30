package pe.leboulevard.demo.domain.roles.service;

import pe.leboulevard.demo.domain.roles.model.RolesModel;

import java.util.List;
import java.util.Optional;

public interface RolesService {
    List<RolesModel> listarTodos();
    RolesModel guardar(RolesModel rol);
    Optional<RolesModel> buscarPorId(Integer id);
    void eliminar(Integer id) throws Exception;
    boolean estaEnUso(Integer idRol);
}