package pe.leboulevard.demo.domain.empleados.model;

import lombok.Getter;
import lombok.Setter;
import pe.leboulevard.demo.domain.cargos.model.CargosModel;
import pe.leboulevard.demo.domain.departamentos.model.DepartamentosModel;
import pe.leboulevard.demo.domain.estado.model.EstadoModel;
import pe.leboulevard.demo.domain.estadocivil.model.EstadoCivilModel;
import pe.leboulevard.demo.domain.genero.model.GeneroModel;
import pe.leboulevard.demo.domain.tipodocumento.model.TipoDocumentoModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class EmpleadosModel {

    private Integer idEmpleado;
    private TipoDocumentoModel tipoDocumento;
    private String numeroDocumento;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private GeneroModel genero;
    private EstadoCivilModel estadoCivil;
    private String telefono;
    private String emailPersonal;
    private String emailCorporativo;
    private String direccion;
    private DepartamentosModel departamento;
    private CargosModel cargo;
    private LocalDate fechaIngreso;
    private BigDecimal salario;
    private EstadoModel estado;
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaActualizacion;
    private String usuarioActualizacion;

}