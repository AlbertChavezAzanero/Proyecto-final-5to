package pe.leboulevard.demo.infrastructure.usuarios.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.leboulevard.demo.domain.usuarios.model.UsuariosModel;
import pe.leboulevard.demo.infrastructure.usuarios.entity.UsuariosEntity;

@Mapper(componentModel = "spring")
public interface UsuariosMapper {

    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "usuarioCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "usuarioActualizacion", ignore = true)
    UsuariosModel toModel(UsuariosEntity entity);

    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "usuarioCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "usuarioActualizacion", ignore = true)
    UsuariosEntity toEntity(UsuariosModel model);
}