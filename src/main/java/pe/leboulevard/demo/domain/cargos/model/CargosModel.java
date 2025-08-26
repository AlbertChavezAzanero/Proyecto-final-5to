package pe.leboulevard.demo.domain.cargos.model;

import lombok.Getter;
import lombok.Setter;
import pe.leboulevard.demo.domain.departamentos.model.DepartamentosModel;
import java.time.LocalDateTime;

@Getter
@Setter
public class CargosModel {
    private Integer idCargo;
    private String nombre;
    private String descripcion;
    private DepartamentosModel departamento;
    private Boolean activo;
    // --- CAMPOS AÑADIDOS (Opción 2) ---
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaActualizacion;
    private String usuarioActualizacion;
}
