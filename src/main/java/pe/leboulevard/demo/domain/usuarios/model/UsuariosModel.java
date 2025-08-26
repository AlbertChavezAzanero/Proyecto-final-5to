package pe.leboulevard.demo.domain.usuarios.model;

import lombok.Getter;
import lombok.Setter;
import pe.leboulevard.demo.domain.empleados.model.EmpleadosModel;
import pe.leboulevard.demo.domain.roles.model.RolesModel;
import java.time.LocalDateTime;

@Getter
@Setter
public class UsuariosModel {
    private Integer idUsuario;
    private String username;
    private String email;
    private String passwordHash;
    private Boolean estaActivo;
    private RolesModel rol;
    private EmpleadosModel empleado;
    private LocalDateTime ultimoAcceso;
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaActualizacion;
    private String usuarioActualizacion;
}
