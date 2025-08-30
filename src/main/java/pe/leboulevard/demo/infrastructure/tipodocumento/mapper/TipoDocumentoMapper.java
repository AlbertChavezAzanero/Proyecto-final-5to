package pe.leboulevard.demo.infrastructure.tipodocumento.mapper;

import org.mapstruct.Mapper;
import pe.leboulevard.demo.domain.tipodocumento.model.TipoDocumentoModel;
import pe.leboulevard.demo.infrastructure.tipodocumento.entity.TipoDocumentoEntity;

@Mapper(componentModel = "spring")
public interface TipoDocumentoMapper {
    TipoDocumentoModel toModel(TipoDocumentoEntity entity);
    TipoDocumentoEntity toEntity(TipoDocumentoModel model);
}