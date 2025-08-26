package pe.leboulevard.demo.domain.empleados.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal; // <-- SE AÑADE ESTE IMPORT
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class EmpleadosModel {
    private Integer idEmpleado;
    private String numeroDocumento;
    private String nombres;
    private String apellidos;
    private LocalDate fechaIngreso;
    private Integer idGenero;
    private Integer idEstadoCivil;

    private BigDecimal salario; // <-- SE CAMBIÓ Double POR BigDecimal

    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaActualizacion;
    private String usuarioActualizacion;
}