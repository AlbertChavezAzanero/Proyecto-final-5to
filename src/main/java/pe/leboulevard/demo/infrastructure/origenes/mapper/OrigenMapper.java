package pe.leboulevard.demo.infrastructure.origenes.mapper;
import org.mapstruct.Mapper;
import pe.leboulevard.demo.domain.origenes.model.OrigenModel;
import pe.leboulevard.demo.infrastructure.origenes.entity.OrigenesEntity;

@Mapper(componentModel = "spring")
public interface OrigenMapper {
    OrigenModel toModel(OrigenesEntity entity);
}