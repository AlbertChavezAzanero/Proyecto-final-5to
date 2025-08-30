package pe.leboulevard.demo.domain.roles.model;

import lombok.Getter;
import lombok.Setter;
import pe.leboulevard.demo.domain.permisos.model.PermisosModel;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RolesModel {
    private Integer idRol;
    private String nombre;
    private String descripcion;
    private Boolean activo;
    private Set<PermisosModel> permisos = new HashSet<>();
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaActualizacion;
    private String usuarioActualizacion;
}