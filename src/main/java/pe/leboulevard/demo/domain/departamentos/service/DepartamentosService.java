package pe.leboulevard.demo.domain.departamentos.service;

import pe.leboulevard.demo.domain.departamentos.model.DepartamentosModel;
import java.util.List;
import java.util.Optional;

public interface DepartamentosService {

    List<DepartamentosModel> listarTodosLosDepartamentos();

    Optional<DepartamentosModel> buscarPorId(Integer id);

    DepartamentosModel guardarDepartamento(DepartamentosModel departamento);

    void eliminarDepartamento(Integer id);

    // --- ¡AÑADE ESTA LÍNEA QUE FALTA AQUÍ! ---
    void cambiarEstadoDepartamento(Integer id);
}