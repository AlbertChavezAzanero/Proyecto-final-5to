package pe.leboulevard.demo.infrastructure.origenes.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.leboulevard.demo.infrastructure.origenes.entity.OrigenesEntity;

public interface OrigenesRepositoryJpa extends JpaRepository<OrigenesEntity, Integer> {}