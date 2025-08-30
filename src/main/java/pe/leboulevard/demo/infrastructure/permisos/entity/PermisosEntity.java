package pe.leboulevard.demo.infrastructure.permisos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permisos")
@Getter
@Setter
public class PermisosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private Integer idPermiso;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_creacion")
    private String usuarioCreacion;

    // Relación inversa para saber qué roles tienen este permiso
    @ManyToMany(mappedBy = "permisos")
    private Set<pe.leboulevard.demo.infrastructure.roles.entity.RolesEntity> roles = new HashSet<>();
}