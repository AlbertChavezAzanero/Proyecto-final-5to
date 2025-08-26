package pe.leboulevard.demo.domain.departamentos.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartamentosModel {
    private Integer idDepartamento;
    private String nombre;
    private String descripcion;
    private Boolean activo;
}
