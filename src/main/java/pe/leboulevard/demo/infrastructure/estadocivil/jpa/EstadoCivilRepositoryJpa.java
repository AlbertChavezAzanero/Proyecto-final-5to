package pe.leboulevard.demo.infrastructure.estadocivil.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.leboulevard.demo.infrastructure.estadocivil.entity.EstadoCivilEntity;

public interface EstadoCivilRepositoryJpa extends JpaRepository<EstadoCivilEntity, Integer> {
}