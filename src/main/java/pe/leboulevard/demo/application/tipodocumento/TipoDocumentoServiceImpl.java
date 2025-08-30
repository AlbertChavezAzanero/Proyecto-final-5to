package pe.leboulevard.demo.application.tipodocumento;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.leboulevard.demo.domain.tipodocumento.model.TipoDocumentoModel;
import pe.leboulevard.demo.domain.tipodocumento.service.TipoDocumentoService;
import pe.leboulevard.demo.infrastructure.tipodocumento.jpa.TipoDocumentoRepositoryJpa;
import pe.leboulevard.demo.infrastructure.tipodocumento.mapper.TipoDocumentoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

    private final TipoDocumentoRepositoryJpa tipoDocumentoRepositoryJpa;
    private final TipoDocumentoMapper tipoDocumentoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TipoDocumentoModel> listarTodos() {
        return tipoDocumentoRepositoryJpa.findAll()
                .stream()
                .map(tipoDocumentoMapper::toModel)
                .collect(Collectors.toList());
    }
}