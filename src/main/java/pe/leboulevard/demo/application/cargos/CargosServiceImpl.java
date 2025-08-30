package pe.leboulevard.demo.application.cargos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.leboulevard.demo.domain.cargos.model.CargosModel;
import pe.leboulevard.demo.domain.cargos.service.CargosService;
import pe.leboulevard.demo.infrastructure.cargos.entity.CargosEntity;
import pe.leboulevard.demo.infrastructure.cargos.jpa.CargosRepositoryJpa;
import pe.leboulevard.demo.infrastructure.cargos.mapper.CargosMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CargosServiceImpl implements CargosService {

    private final CargosRepositoryJpa cargosRepositoryJpa;
    private final CargosMapper cargosMapper;

    @Override
    @Transactional
    public CargosModel guardarCargo(CargosModel cargo) {
        cargo.setUsuarioActualizacion("admin-system");
        if (cargo.getIdCargo() == null) {
            cargo.setUsuarioCreacion("admin-system");
            cargo.setActivo(true);
        }

        CargosEntity cargoEntity = cargosMapper.toEntity(cargo);

        if (cargoEntity.getIdCargo() != null) {
            cargosRepositoryJpa.findById(cargoEntity.getIdCargo()).ifPresent(original -> {
                cargoEntity.setFechaCreacion(original.getFechaCreacion());
                cargoEntity.setUsuarioCreacion(original.getUsuarioCreacion());
            });
        }

        CargosEntity savedEntity = cargosRepositoryJpa.save(cargoEntity);
        return cargosMapper.toModel(savedEntity);
    }

    @Override
    @Transactional
    public void cambiarEstadoCargo(Integer id) {
        cargosRepositoryJpa.findById(id).ifPresent(cargo -> {
            // --- LÓGICA CORREGIDA A PRUEBA DE NULOS ---
            boolean estadoActual = cargo.getActivo() != null && cargo.getActivo();
            cargo.setActivo(!estadoActual); // Invierte el estado de forma segura
            cargosRepositoryJpa.save(cargo);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<CargosModel> listarTodosLosCargos() {
        return cargosRepositoryJpa.findAll().stream()
                .map(cargosMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CargosModel> buscarPorId(Integer id) {
        return cargosRepositoryJpa.findById(id).map(cargosMapper::toModel);
    }

    @Override
    @Transactional
    public void eliminarCargo(Integer id) {
        cargosRepositoryJpa.deleteById(id);
    }
}