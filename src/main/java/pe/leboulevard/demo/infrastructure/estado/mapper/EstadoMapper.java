// Ejemplo para EstadoMapper.java
package pe.leboulevard.demo.infrastructure.estado.mapper;

import org.mapstruct.Mapper;
import pe.leboulevard.demo.domain.estado.model.EstadoModel;
import pe.leboulevard.demo.infrastructure.estado.entity.EstadoEntity;

@Mapper(componentModel = "spring")
public interface EstadoMapper {
    EstadoModel toModel(EstadoEntity entity);
    EstadoEntity toEntity(EstadoModel model);
}