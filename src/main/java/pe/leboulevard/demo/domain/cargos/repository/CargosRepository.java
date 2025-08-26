package pe.leboulevard.demo.domain.cargos.repository;

import pe.leboulevard.demo.domain.cargos.model.CargosModel;

import java.util.List;
import java.util.Optional;

public interface CargosRepository  {
    Optional<CargosModel> buscarPorId(Long id_cargo);

    List<CargosModel> todosLosCargos();
}
