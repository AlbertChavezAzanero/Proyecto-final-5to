package pe.leboulevard.demo.infrastructure.cargos.mapper;

import org.mapstruct.Mapper;
import pe.leboulevard.demo.domain.cargos.model.CargosModel;
import pe.leboulevard.demo.infrastructure.cargos.entity.CargosEntity;
import pe.leboulevard.demo.infrastructure.departamentos.mapper.DepartamentosMapper;

@Mapper(componentModel = "spring", uses = {DepartamentosMapper.class})
public interface CargosMapper {
    CargosModel toModel(CargosEntity entity);
    CargosEntity toEntity(CargosModel model);
}