package pe.leboulevard.demo.application.empleados;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.leboulevard.demo.domain.empleados.model.EmpleadosModel;
import pe.leboulevard.demo.domain.empleados.service.EmpleadosService;
import pe.leboulevard.demo.infrastructure.empleados.entity.EmpleadosEntity;
import pe.leboulevard.demo.infrastructure.empleados.jpa.EmpleadosRepositoryJpa;
import pe.leboulevard.demo.infrastructure.empleados.mapper.EmpleadosMapper;

@Service
@RequiredArgsConstructor
public class EmpleadosServiceImpl implements EmpleadosService {

    private final EmpleadosRepositoryJpa empleadosRepositoryJpa;
    private final EmpleadosMapper empleadosMapper;

    @Override
    public EmpleadosModel guardarEmpleado(EmpleadosModel empleadosModel) {
        EmpleadosEntity entity = empleadosMapper.toEntity(empleadosModel);
        entity.setUsuarioCreacion("system-register");
        entity.setUsuarioActualizacion("system-register");
        EmpleadosEntity savedEntity = empleadosRepositoryJpa.save(entity);
        return empleadosMapper.toModel(savedEntity);
    }
}
