package pe.leboulevard.demo.domain.cargos.service;

import pe.leboulevard.demo.domain.cargos.model.CargosModel;
import java.util.List;
import java.util.Optional;

public interface CargosService {

    List<CargosModel> listarTodosLosCargos();

    Optional<CargosModel> buscarPorId(Integer id);

    CargosModel guardarCargo(CargosModel cargo);

    void eliminarCargo(Integer id);

    void cambiarEstadoCargo(Integer id); // Asegúrate que esta línea exista
}