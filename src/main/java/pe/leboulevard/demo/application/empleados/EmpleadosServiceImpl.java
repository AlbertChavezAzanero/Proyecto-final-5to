package pe.leboulevard.demo.application.empleados;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.leboulevard.demo.domain.common.exceptions.DuplicateDataException;
import pe.leboulevard.demo.domain.empleados.model.EmpleadosModel;
import pe.leboulevard.demo.domain.empleados.service.EmpleadosService;
import pe.leboulevard.demo.domain.estado.model.EstadoModel;
import pe.leboulevard.demo.infrastructure.cargos.jpa.CargosRepositoryJpa;
import pe.leboulevard.demo.infrastructure.departamentos.jpa.DepartamentosRepositoryJpa;
import pe.leboulevard.demo.infrastructure.empleados.entity.EmpleadosEntity;
import pe.leboulevard.demo.infrastructure.empleados.jpa.EmpleadosRepositoryJpa;
import pe.leboulevard.demo.infrastructure.empleados.mapper.EmpleadosMapper;
import pe.leboulevard.demo.infrastructure.estado.entity.EstadoEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpleadosServiceImpl implements EmpleadosService {

    private final EmpleadosRepositoryJpa empleadosRepositoryJpa;
    private final EmpleadosMapper empleadosMapper;
    private final CargosRepositoryJpa cargosRepositoryJpa;
    private final DepartamentosRepositoryJpa departamentosRepositoryJpa;

    @Override
    @Transactional
    public EmpleadosModel guardarEmpleado(EmpleadosModel empleadoModel) {
        // Validación de DNI duplicado
        empleadosRepositoryJpa.findByNumeroDocumento(empleadoModel.getNumeroDocumento())
                .ifPresent(empleadoExistente -> {
                    boolean esElMismoEmpleado = empleadoModel.getIdEmpleado() != null &&
                            empleadoExistente.getIdEmpleado().equals(empleadoModel.getIdEmpleado());
                    if (!esElMismoEmpleado) {
                        throw new DuplicateDataException("El DNI '" + empleadoModel.getNumeroDocumento() + "' ya está registrado.");
                    }
                });

        EmpleadosEntity entidadParaGuardar = empleadosMapper.toEntity(empleadoModel);

        // 1. Buscamos las entidades relacionadas para evitar el error TransientObjectException
        if (empleadoModel.getCargo() != null && empleadoModel.getCargo().getIdCargo() != null) {
            cargosRepositoryJpa.findById(empleadoModel.getCargo().getIdCargo())
                    .ifPresent(entidadParaGuardar::setCargo);
        }

        if (empleadoModel.getDepartamento() != null && empleadoModel.getDepartamento().getIdDepartamento() != null) {
            departamentosRepositoryJpa.findById(empleadoModel.getDepartamento().getIdDepartamento())
                    .ifPresent(entidadParaGuardar::setDepartamento);
        }

        // 2. Asignamos los campos de auditoría
        entidadParaGuardar.setUsuarioActualizacion("admin-system");

        if (entidadParaGuardar.getIdEmpleado() == null) { // Si es un empleado NUEVO
            entidadParaGuardar.setUsuarioCreacion("admin-system");
            entidadParaGuardar.setFechaIngreso(LocalDate.now());

            EstadoEntity estadoActivo = new EstadoEntity();
            estadoActivo.setIdEstado(1);
            entidadParaGuardar.setEstado(estadoActivo);
        } else { // Si estamos EDITANDO, conservamos los datos que no se pueden cambiar
            empleadosRepositoryJpa.findById(entidadParaGuardar.getIdEmpleado().longValue())
                    .ifPresent(entidadOriginal -> {
                        entidadParaGuardar.setFechaCreacion(entidadOriginal.getFechaCreacion());
                        entidadParaGuardar.setUsuarioCreacion(entidadOriginal.getUsuarioCreacion());
                        entidadParaGuardar.setFechaIngreso(entidadOriginal.getFechaIngreso());
                    });
        }

        // 3. Guardamos la entidad
        EmpleadosEntity savedEntity = empleadosRepositoryJpa.save(entidadParaGuardar);
        return empleadosMapper.toModel(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmpleadosModel> listarTodosLosEmpleados() {
        return empleadosRepositoryJpa.findAll().stream().map(empleadosMapper::toModel).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmpleadosModel> buscarPorId(Integer id) {
        return empleadosRepositoryJpa.findById(id.longValue()).map(empleadosMapper::toModel);
    }

    @Override
    @Transactional
    public void cambiarEstadoEmpleado(Integer id) {
        empleadosRepositoryJpa.findById(id.longValue()).ifPresent(empleadoEntity -> {
            boolean estadoActual = empleadoEntity.getEstado() != null && empleadoEntity.getEstado().getIdEstado() == 1;
            int nuevoIdEstado = estadoActual ? 2 : 1;

            EstadoEntity nuevoEstado = new EstadoEntity();
            nuevoEstado.setIdEstado(nuevoIdEstado);
            empleadoEntity.setEstado(nuevoEstado);

            empleadosRepositoryJpa.save(empleadoEntity);
        });
    }
}