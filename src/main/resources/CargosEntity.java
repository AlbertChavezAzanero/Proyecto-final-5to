package pe.leboulevard.demo.infrastructure.cargos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pe.leboulevard.demo.infrastructure.departamentos.entity.DepartamentosEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "cargos")
@Getter
@Setter
public class CargosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cargo")
    private Integer idCargo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento")
    private DepartamentosEntity departamento;

    @Column(name = "activo")
    private Boolean activo;

    // --- CAMPOS DE AUDITORÍA CORREGIDOS ---

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
