package pe.leboulevard.demo.infrastructure.clientes.mapper;

import org.mapstruct.Mapper;
import pe.leboulevard.demo.domain.clientes.model.ClienteModel;
import pe.leboulevard.demo.infrastructure.clientes.entity.ClientesEntity;
import pe.leboulevard.demo.infrastructure.origenes.mapper.OrigenMapper;
import pe.leboulevard.demo.infrastructure.tipodocumento.mapper.TipoDocumentoMapper;

@Mapper(componentModel = "spring", uses = {OrigenMapper.class, TipoDocumentoMapper.class})
public interface ClienteMapper {
    ClienteModel toModel(ClientesEntity entity);
    ClientesEntity toEntity(ClienteModel model);
}