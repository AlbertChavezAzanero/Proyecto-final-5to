package pe.leboulevard.demo.infrastructure.estadocivil.mapper;

import org.mapstruct.Mapper;
import pe.leboulevard.demo.domain.estadocivil.model.EstadoCivilModel;
import pe.leboulevard.demo.infrastructure.estadocivil.entity.EstadoCivilEntity;

@Mapper(componentModel = "spring")
public interface EstadoCivilMapper {
    EstadoCivilModel toModel(EstadoCivilEntity entity);
    EstadoCivilEntity toEntity(EstadoCivilModel model);
}