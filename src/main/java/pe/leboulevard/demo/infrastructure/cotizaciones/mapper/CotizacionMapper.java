package pe.leboulevard.demo.infrastructure.cotizaciones.mapper;

import org.mapstruct.Mapper;
import pe.leboulevard.demo.domain.cotizaciones.model.CotizacionModel;
import pe.leboulevard.demo.infrastructure.clientes.mapper.ClienteMapper;
import pe.leboulevard.demo.infrastructure.cotizaciones.entity.CotizacionEntity;
import pe.leboulevard.demo.infrastructure.detallecotizacion.mapper.DetalleCotizacionMapper;
import pe.leboulevard.demo.infrastructure.usuarios.mapper.UsuariosMapper;

@Mapper(componentModel = "spring", uses = {ClienteMapper.class, UsuariosMapper.class, DetalleCotizacionMapper.class})
public interface CotizacionMapper {
    // Simplemente declara los métodos, sin "new *" ni punto y coma al final
    CotizacionModel toModel(CotizacionEntity entity);
    CotizacionEntity toEntity(CotizacionModel model);
}