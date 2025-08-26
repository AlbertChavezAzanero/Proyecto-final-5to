package pe.leboulevard.demo.infrastructure.cargos.mapper;

import org.mapstruct.Mapper;
import pe.leboulevard.demo.domain.cargos.model.CargosModel;
import pe.leboulevard.demo.infrastructure.cargos.entity.CargosEntity;

@Mapper(componentModel = "spring")
public interface CargosMapper {

    // Ya no se ignoran campos porque ahora existen en el Model
    CargosModel toModel(CargosEntity entity);
    CargosEntity toEntity(CargosModel model);
}