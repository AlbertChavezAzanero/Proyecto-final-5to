package pe.leboulevard.demo.infrastructure.permisos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping; // <-- IMPORTANTE
import pe.leboulevard.demo.domain.permisos.model.PermisosModel;
import pe.leboulevard.demo.infrastructure.permisos.entity.PermisosEntity;

@Mapper(componentModel = "spring")
public interface PermisosMapper {

    // --- INICIO DE LA CORRECCIÓN ---
    // Al convertir de Entidad a Modelo, ignoramos la lista de roles para romper el bucle.
    @Mapping(target = "roles", ignore = true)
    PermisosModel toModel(PermisosEntity entity);
    // --- FIN DE LA CORRECCIÓN ---

    PermisosEntity toEntity(PermisosModel model);
}