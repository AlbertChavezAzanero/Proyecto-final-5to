package pe.leboulevard.demo.infrastructure.categoriasproducto.mapper;

import org.mapstruct.Mapper;
import pe.leboulevard.demo.domain.categoriasproducto.model.CategoriasProductoModel;
import pe.leboulevard.demo.infrastructure.categoriasproducto.entity.CategoriasProductoEntity;

@Mapper(componentModel = "spring")
public interface CategoriasProductoMapper {
    CategoriasProductoModel toModel(CategoriasProductoEntity entity);
    CategoriasProductoEntity toEntity(CategoriasProductoModel model);
}