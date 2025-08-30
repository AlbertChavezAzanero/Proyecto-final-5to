package pe.leboulevard.demo.infrastructure.roles.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pe.leboulevard.demo.infrastructure.permisos.entity.PermisosEntity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class RolesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

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

    // --- Relación Muchos-a-Muchos con Permisos ---
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "roles_permisos",
            joinColumns = @JoinColumn(name = "id_rol"),
            inverseJoinColumns = @JoinColumn(name = "id_permiso")
    )
    private Set<PermisosEntity> permisos = new HashSet<>();
}