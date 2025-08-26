package pe.leboulevard.demo.domain.cargos.service;

import pe.leboulevard.demo.domain.cargos.model.CargosModel;
import java.util.Optional;

public interface CargosService {

    /**
     * Guarda un nuevo cargo o actualiza uno existente.
     * @param cargosModel El modelo del cargo con los datos a guardar.
     * @return El modelo del cargo guardado.
     */
    CargosModel guardarCargo(CargosModel cargosModel);

    /**
     * Busca un cargo por su ID.
     * @param id El ID del cargo a buscar.
     * @return Un Optional que contiene el CargosModel si se encuentra, o vacío si no.
     */
    Optional<CargosModel> buscarCargoPorId(Integer id);
}
