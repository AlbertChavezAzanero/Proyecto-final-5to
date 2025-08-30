package pe.leboulevard.demo.domain.empleados.service;

import pe.leboulevard.demo.domain.empleados.model.EmpleadosModel;
import java.util.List;
import java.util.Optional;

public interface EmpleadosService {
    EmpleadosModel guardarEmpleado(EmpleadosModel empleadosModel);
    List<EmpleadosModel> listarTodosLosEmpleados();
    Optional<EmpleadosModel> buscarPorId(Integer id);
    void cambiarEstadoEmpleado(Integer id);
}