package pe.leboulevard.demo.infrastructure.departamentos.mapper;

import org.mapstruct.Mapper;
import pe.leboulevard.demo.domain.departamentos.model.DepartamentosModel;
import pe.leboulevard.demo.infrastructure.departamentos.entity.DepartamentosEntity;

@Mapper(componentModel = "spring")
public interface DepartamentosMapper {
    DepartamentosModel toModel(DepartamentosEntity entity);
    DepartamentosEntity toEntity(DepartamentosModel model);
}