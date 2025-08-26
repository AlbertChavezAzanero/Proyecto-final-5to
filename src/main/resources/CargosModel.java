package pe.leboulevard.demo.domain.cargos.model;

import lombok.Getter;
import lombok.Setter;
import pe.leboulevard.demo.domain.departamentos.model.DepartamentosModel;

@Getter
@Setter
public class CargosModel {
    private Integer idCargo;
    private String nombre;
    private String descripcion;
    private DepartamentosModel departamento;
    private Boolean activo;
}