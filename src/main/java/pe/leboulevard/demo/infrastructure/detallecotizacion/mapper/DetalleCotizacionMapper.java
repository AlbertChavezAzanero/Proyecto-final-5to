package pe.leboulevard.demo.infrastructure.detallecotizacion.mapper;

import org.mapstruct.Mapper;
import pe.leboulevard.demo.domain.detallecotizacion.model.DetalleCotizacionModel;
import pe.leboulevard.demo.infrastructure.detallecotizacion.entity.DetalleCotizacionEntity;
import pe.leboulevard.demo.infrastructure.productos.mapper.ProductosMapper;

@Mapper(componentModel = "spring", uses = {ProductosMapper.class})
public interface DetalleCotizacionMapper {
    DetalleCotizacionModel toModel(DetalleCotizacionEntity entity);
    DetalleCotizacionEntity toEntity(DetalleCotizacionModel model);
}