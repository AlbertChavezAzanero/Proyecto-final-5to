package pe.leboulevard.demo.infrastructure.empleados.mapper;

import org.mapstruct.Mapper;
import pe.leboulevard.demo.domain.empleados.model.EmpleadosModel;
import pe.leboulevard.demo.infrastructure.empleados.entity.EmpleadosEntity;

@Mapper(componentModel = "spring")
public interface EmpleadosMapper {
    EmpleadosEntity toEntity(EmpleadosModel model);
    EmpleadosModel toModel(EmpleadosEntity entity);
}