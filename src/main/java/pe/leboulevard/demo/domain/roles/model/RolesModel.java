package pe.leboulevard.demo.domain.roles.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolesModel {
    private Integer idRol;
    private String nombre;
    private String descripcion;
    private Boolean activo;
}
