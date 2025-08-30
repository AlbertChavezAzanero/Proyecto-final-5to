package pe.leboulevard.demo.infrastructure.estado.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.leboulevard.demo.infrastructure.estado.entity.EstadoEntity;

public interface EstadoRepositoryJpa extends JpaRepository<EstadoEntity, Integer> {
}