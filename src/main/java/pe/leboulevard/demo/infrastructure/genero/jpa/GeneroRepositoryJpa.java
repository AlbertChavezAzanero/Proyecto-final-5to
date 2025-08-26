package pe.leboulevard.demo.infrastructure.genero.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.leboulevard.demo.infrastructure.genero.entity.GeneroEntity;

public interface GeneroRepositoryJpa extends JpaRepository<GeneroEntity, Integer> {
}