package pe.leboulevard.demo.infrastructure.usuarios.mapper;

import org.mapstruct.Mapper;
import pe.leboulevard.demo.domain.usuarios.model.UsuariosModel;
import pe.leboulevard.demo.infrastructure.empleados.mapper.EmpleadosMapper;
import pe.leboulevard.demo.infrastructure.roles.mapper.RolesMapper;
import pe.leboulevard.demo.infrastructure.usuarios.entity.UsuariosEntity;

@Mapper(componentModel = "spring", uses = {RolesMapper.class, EmpleadosMapper.class})
public interface UsuariosMapper {
    UsuariosModel toModel(UsuariosEntity entity);
    UsuariosEntity toEntity(UsuariosModel model);
}