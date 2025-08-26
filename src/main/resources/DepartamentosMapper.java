package pe.leboulevard.demo.infrastructure.departamentos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.leboulevard.demo.domain.departamentos.model.DepartamentosModel;
import pe.leboulevard.demo.infrastructure.departamentos.entity.DepartamentosEntity;

@Mapper(componentModel = "spring")
public interface DepartamentosMapper {

    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "usuarioCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "usuarioActualizacion", ignore = true)
    DepartamentosModel toModel(DepartamentosEntity entity);

    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "usuarioCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "usuarioActualizacion", ignore = true)
    DepartamentosEntity toEntity(DepartamentosModel model);
}+