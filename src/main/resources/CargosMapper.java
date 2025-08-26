package pe.leboulevard.demo.infrastructure.cargos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.leboulevard.demo.domain.cargos.model.CargosModel;
import pe.leboulevard.demo.infrastructure.cargos.entity.CargosEntity;

@Mapper(componentModel = "spring")
public interface CargosMapper {

    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "usuarioCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "usuarioActualizacion", ignore = true)
    CargosModel toModel(CargosEntity entity);

    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "usuarioCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "usuarioActualizacion", ignore = true)
    CargosEntity toEntity(CargosModel model);
}