package pe.leboulevard.demo.application.clientes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.leboulevard.demo.domain.clientes.model.ClienteModel;
import pe.leboulevard.demo.domain.clientes.service.ClienteService;
import pe.leboulevard.demo.infrastructure.clientes.entity.ClientesEntity;
import pe.leboulevard.demo.infrastructure.clientes.jpa.ClientesRepositoryJpa;
import pe.leboulevard.demo.infrastructure.clientes.mapper.ClienteMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClientesRepositoryJpa clientesRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public List<ClienteModel> listarTodos() {
        return clientesRepository.findAll().stream().map(clienteMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Optional<ClienteModel> buscarPorId(Integer id) {
        return clientesRepository.findById(id).map(clienteMapper::toModel);
    }

    @Override
    public ClienteModel guardarCliente(ClienteModel cliente) {
        ClientesEntity entity = clienteMapper.toEntity(cliente);

        if (entity.getIdCliente() == null) {
            entity.setUsuarioCreacion("admin-system");
        }
        entity.setUsuarioActualizacion("admin-system");

        ClientesEntity savedEntity = clientesRepository.save(entity);
        return clienteMapper.toModel(savedEntity);
    }

    @Override
    public void eliminarCliente(Integer id) {
        clientesRepository.deleteById(id);
    }
}