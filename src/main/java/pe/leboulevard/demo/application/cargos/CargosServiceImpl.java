package pe.leboulevard.demo.application.cargos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.leboulevard.demo.domain.cargos.model.CargosModel;
import pe.leboulevard.demo.domain.cargos.service.CargosService;
import pe.leboulevard.demo.infrastructure.cargos.entity.CargosEntity;
import pe.leboulevard.demo.infrastructure.cargos.jpa.CargosRepositoryJpa;
import pe.leboulevard.demo.infrastructure.cargos.mapper.CargosMapper;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CargosServiceImpl implements CargosService {

    private final CargosRepositoryJpa cargosRepositoryJpa;
    private final CargosMapper cargosMapper;

    @Override
    public CargosModel guardarCargo(CargosModel cargosModel) {
        // 1. Convertimos el Model a una Entity.
        CargosEntity cargosEntity = cargosMapper.toEntity(cargosModel);

        // 2. Guardamos la Entity en la base de datos.
        CargosEntity cargoGuardado = cargosRepositoryJpa.save(cargosEntity);

        // 3. Convertimos la Entity guardada de nuevo a un Model.
        return cargosMapper.toModel(cargoGuardado);
    }

    @Override
    public Optional<CargosModel> buscarCargoPorId(Integer id) {
        return cargosRepositoryJpa.findById(Long.valueOf(id))
                .map(cargosMapper::toModel);
    }
}