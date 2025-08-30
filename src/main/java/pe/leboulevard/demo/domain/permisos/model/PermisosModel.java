package pe.leboulevard.demo.domain.permisos.model;

import lombok.Getter;
import lombok.Setter;
import pe.leboulevard.demo.domain.roles.model.RolesModel; // Importante para la relación

import java.time.LocalDateTime;
import java.util.Set; // Importante para la colección de roles

@Getter
@Setter
public class PermisosModel {
    private Integer idPermiso;
    private String nombre;
    private String descripcion;

    // --- CAMPOS AÑADIDOS PARA SOLUCIONAR LA ADVERTENCIA ---
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private Set<RolesModel> roles;
}