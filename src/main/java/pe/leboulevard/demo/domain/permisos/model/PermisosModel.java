package pe.leboulevard.demo.domain.permisos.model;


import lombok.Data;

import java.time.LocalDate;

@Data
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class PermisosModel {

    private Long id_permiso;
    private Long id_usuario;
    private Long id_rol;
    private Boolean activo;
    //Campos dde auditoria
    private LocalDate fecha_creacion;
    private String usuario_creacion;
    private LocalDate fecha_actualizacion;
    private String usuario_actualizacion;


}
