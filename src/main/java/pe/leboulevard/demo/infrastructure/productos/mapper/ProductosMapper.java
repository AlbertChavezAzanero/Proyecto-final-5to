package pe.leboulevard.demo.infrastructure.productos.mapper;

import org.mapstruct.Mapper;
import pe.leboulevard.demo.domain.productos.model.ProductosModel;
import pe.leboulevard.demo.infrastructure.categoriasproducto.mapper.CategoriasProductoMapper;
import pe.leboulevard.demo.infrastructure.productos.entity.ProductosEntity;

@Mapper(componentModel = "spring", uses = {CategoriasProductoMapper.class})
public interface ProductosMapper {
    ProductosModel toModel(ProductosEntity entity);
    ProductosEntity toEntity(ProductosModel model);
}