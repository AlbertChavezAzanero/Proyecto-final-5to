package pe.leboulevard.demo.infrastructure.permisos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pe.leboulevard.demo.infrastructure.roles.entity.RolesEntity;
import pe.leboulevard.demo.infrastructure.usuarios.entity.UsuariosEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "permisos")
@Getter
@Setter
public class PermisosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private Integer idPermiso; // <-- CORREGIDO de Long a Integer

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuariosEntity usuario;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private RolesEntity rol;

    @Column(name = "activo")
    private Boolean activo;

    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_creacion", nullable = false, length = 50)
    private String usuarioCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Column(name = "usuario_actualizacion", nullable = false, length = 50)
    private String usuarioActualizacion;
}
