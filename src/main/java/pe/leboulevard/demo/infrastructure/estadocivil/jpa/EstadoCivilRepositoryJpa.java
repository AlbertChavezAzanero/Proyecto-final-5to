package pe.leboulevard.demo.infrastructure.estadocivil.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.leboulevard.demo.infrastructure.estadocivil.entity.EstadoCivilEntity;

@Repository
public interface EstadoCivilRepositoryJpa extends JpaRepository<EstadoCivilEntity, Integer> {
}