package pe.leboulevard.demo.infrastructure.empleados.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pe.leboulevard.demo.infrastructure.cargos.entity.CargosEntity;
import pe.leboulevard.demo.infrastructure.departamentos.entity.DepartamentosEntity;
import pe.leboulevard.demo.infrastructure.estado.entity.EstadoEntity;
import pe.leboulevard.demo.infrastructure.estadocivil.entity.EstadoCivilEntity;
import pe.leboulevard.demo.infrastructure.genero.entity.GeneroEntity;
import pe.leboulevard.demo.infrastructure.tipodocumento.entity.TipoDocumentoEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "empleados")
@Getter
@Setter
public class EmpleadosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_documento")
    private TipoDocumentoEntity tipoDocumento;

    @Column(name = "numero_documento", nullable = false, unique = true, length = 15)
    private String numeroDocumento;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genero", nullable = false)
    private GeneroEntity genero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_civil", nullable = false)
    private EstadoCivilEntity estadoCivil;

    @Column(name = "telefono", length = 50)
    private String telefono;

    @Column(name = "email_personal", length = 100)
    private String emailPersonal;

    @Column(name = "email_corporativo", length = 100)
    private String emailCorporativo;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento")
    private DepartamentosEntity departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cargo")
    private CargosEntity cargo;

    @Column(name = "fecha_ingreso", nullable = false, updatable = false)
    private LocalDate fechaIngreso;

    @Column(name = "salario", nullable = false)
    private BigDecimal salario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado")
    private EstadoEntity estado;

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