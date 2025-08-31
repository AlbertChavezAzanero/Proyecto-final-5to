package pe.leboulevard.demo.infrastructure.clientes.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.leboulevard.demo.infrastructure.clientes.entity.ClientesEntity;

public interface ClientesRepositoryJpa extends JpaRepository<ClientesEntity, Integer> {}