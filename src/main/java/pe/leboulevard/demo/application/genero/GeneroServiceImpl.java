package pe.leboulevard.demo.application.genero;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.leboulevard.demo.domain.genero.model.GeneroModel;
import pe.leboulevard.demo.domain.genero.service.GeneroService;
import pe.leboulevard.demo.infrastructure.genero.jpa.GeneroRepositoryJpa;
import pe.leboulevard.demo.infrastructure.genero.mapper.GeneroMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeneroServiceImpl implements GeneroService {

    private final GeneroRepositoryJpa generoRepositoryJpa;
    private final GeneroMapper generoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<GeneroModel> listarTodos() {
        return generoRepositoryJpa.findAll()
                .stream()
                .map(generoMapper::toModel)
                .collect(Collectors.toList());
    }
}