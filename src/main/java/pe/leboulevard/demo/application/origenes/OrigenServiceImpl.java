package pe.leboulevard.demo.application.origenes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.leboulevard.demo.domain.origenes.model.OrigenModel;
import pe.leboulevard.demo.domain.origenes.service.OrigenService;
import pe.leboulevard.demo.infrastructure.origenes.jpa.OrigenesRepositoryJpa;
import pe.leboulevard.demo.infrastructure.origenes.mapper.OrigenMapper;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrigenServiceImpl implements OrigenService {
    private final OrigenesRepositoryJpa origenesRepository;
    private final OrigenMapper origenMapper;

    @Override
    public List<OrigenModel> listarTodos() {
        return origenesRepository.findAll().stream()
                .map(origenMapper::toModel)
                .collect(Collectors.toList());
    }
}