package pe.leboulevard.demo.infrastructure.cargos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.leboulevard.demo.infrastructure.cargos.entity.CargosEntity;

public interface CargosRepositoryJpa extends JpaRepository<CargosEntity,Long> {
}
