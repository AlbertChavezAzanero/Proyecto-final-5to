package pe.leboulevard.demo.infrastructure.usuarios.mapper;

import org.mapstruct.Mapper;
import pe.leboulevard.demo.domain.usuarios.model.UsuariosModel;
import pe.leboulevard.demo.infrastructure.usuarios.entity.UsuariosEntity;

@Mapper(componentModel = "spring")
public interface UsuariosMapper {

    // Ya no se ignoran campos porque ahora existen en el Model
    UsuariosModel toModel(UsuariosEntity entity);
    UsuariosEntity toEntity(UsuariosModel model);
}
