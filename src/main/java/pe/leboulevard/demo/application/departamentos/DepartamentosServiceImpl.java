package pe.leboulevard.demo.application.departamentos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.leboulevard.demo.domain.departamentos.model.DepartamentosModel;
import pe.leboulevard.demo.domain.departamentos.service.DepartamentosService;
import pe.leboulevard.demo.infrastructure.departamentos.entity.DepartamentosEntity;
import pe.leboulevard.demo.infrastructure.departamentos.jpa.DepartamentosRepositoryJpa;
import pe.leboulevard.demo.infrastructure.departamentos.mapper.DepartamentosMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // <-- ESTA ANOTACIÓN YA CREA EL CONSTRUCTOR
public class DepartamentosServiceImpl implements DepartamentosService {

    private final DepartamentosRepositoryJpa departamentosRepositoryJpa;
    private final DepartamentosMapper departamentosMapper;

    // NO es necesario escribir un constructor aquí. @RequiredArgsConstructor lo hace automáticamente.

    @Override
    @Transactional
    public DepartamentosModel guardarDepartamento(DepartamentosModel departamento) {
        departamento.setUsuarioActualizacion("admin-system");
        if (departamento.getIdDepartamento() == null) {
            departamento.setUsuarioCreacion("admin-system");
            departamento.setActivo(true);
        }

        DepartamentosEntity entity = departamentosMapper.toEntity(departamento);

        if (entity.getIdDepartamento() != null) {
            departamentosRepositoryJpa.findById(entity.getIdDepartamento()).ifPresent(original -> {
                entity.setFechaCreacion(original.getFechaCreacion());
                entity.setUsuarioCreacion(original.getUsuarioCreacion());
            });
        }

        DepartamentosEntity savedEntity = departamentosRepositoryJpa.save(entity);
        return departamentosMapper.toModel(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartamentosModel> listarTodosLosDepartamentos() {
        return departamentosRepositoryJpa.findAll().stream()
                .map(departamentosMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DepartamentosModel> buscarPorId(Integer id) {
        return departamentosRepositoryJpa.findById(id).map(departamentosMapper::toModel);
    }

    @Override
    @Transactional
    public void cambiarEstadoDepartamento(Integer id) {
        departamentosRepositoryJpa.findById(id).ifPresent(departamento -> {
            departamento.setActivo(!departamento.getActivo());
            departamentosRepositoryJpa.save(departamento);
        });
    }

    @Override
    @Transactional
    public void eliminarDepartamento(Integer id) {
        departamentosRepositoryJpa.deleteById(id);
    }
}