package pe.leboulevard.demo.domain.clientes.service;

import pe.leboulevard.demo.domain.clientes.model.ClienteModel;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<ClienteModel> listarTodos();
    Optional<ClienteModel> buscarPorId(Integer id);
    ClienteModel guardarCliente(ClienteModel cliente);
    void eliminarCliente(Integer id);
}