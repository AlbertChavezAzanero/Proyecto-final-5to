package pe.leboulevard.demo.infrastructure.empleados.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal; // <-- CAMBIO 1: Se importa BigDecimal
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="empleados")
@Getter
@Setter
public class EmpleadosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @Column(name = "id_tipo_documento")
    private Integer idTipoDocumento;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "id_genero")
    private Integer idGenero;

    @Column(name = "id_estado_civil")
    private Integer idEstadoCivil;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email_personal")
    private String emailPersonal;

    @Column(name = "email_corporativo")
    private String emailCorporativo;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "id_departamento")
    private Integer idDepartamento;

    @Column(name = "id_cargo")
    private Integer idCargo;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(name = "fecha_cese")
    private LocalDate fechaCese;

    @Column(name = "salario")
    private BigDecimal salario; // <-- CAMBIO 2: El tipo de dato ahora es BigDecimal

    @Column(name = "id_estado")
    private Integer idEstado;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_creacion", nullable = false, updatable = false, length = 50)
    private String usuarioCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

    @Column(name = "usuario_actualizacion", nullable = false, length = 50)
    private String usuarioActualizacion;
}