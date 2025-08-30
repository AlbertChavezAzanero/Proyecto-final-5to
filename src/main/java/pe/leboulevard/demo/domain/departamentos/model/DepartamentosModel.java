package pe.leboulevard.demo.domain.departamentos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentosModel {

    private Integer idDepartamento;
    private String nombre;
    private String descripcion;
    private Boolean activo;
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaActualizacion;
    private String usuarioActualizacion;

}