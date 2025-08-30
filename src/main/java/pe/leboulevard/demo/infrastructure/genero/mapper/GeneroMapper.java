package pe.leboulevard.demo.infrastructure.genero.mapper;

import org.mapstruct.Mapper;
import pe.leboulevard.demo.domain.genero.model.GeneroModel;
import pe.leboulevard.demo.infrastructure.genero.entity.GeneroEntity;

@Mapper(componentModel = "spring")
public interface GeneroMapper {
    GeneroModel toModel(GeneroEntity entity);
    GeneroEntity toEntity(GeneroModel model);
}