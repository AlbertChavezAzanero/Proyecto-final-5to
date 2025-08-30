package pe.leboulevard.demo.application.estadocivil;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.leboulevard.demo.domain.estadocivil.model.EstadoCivilModel;
import pe.leboulevard.demo.domain.estadocivil.service.EstadoCivilService;
import pe.leboulevard.demo.infrastructure.estadocivil.jpa.EstadoCivilRepositoryJpa;
import pe.leboulevard.demo.infrastructure.estadocivil.mapper.EstadoCivilMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstadoCivilServiceImpl implements EstadoCivilService {

    private final EstadoCivilRepositoryJpa estadoCivilRepositoryJpa;
    private final EstadoCivilMapper estadoCivilMapper;

    @Override
    @Transactional(readOnly = true)
    public List<EstadoCivilModel> listarTodos() {
        return estadoCivilRepositoryJpa.findAll()
                .stream()
                .map(estadoCivilMapper::toModel)
                .collect(Collectors.toList());
    }
}