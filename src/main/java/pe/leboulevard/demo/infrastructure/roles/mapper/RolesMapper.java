package pe.leboulevard.demo.infrastructure.roles.mapper;

import org.mapstruct.Mapper;
import pe.leboulevard.demo.domain.roles.model.RolesModel;
import pe.leboulevard.demo.infrastructure.roles.entity.RolesEntity;

@Mapper(componentModel = "spring")
public interface RolesMapper {
    RolesModel toModel(RolesEntity entity);
    RolesEntity toEntity(RolesModel model);
}