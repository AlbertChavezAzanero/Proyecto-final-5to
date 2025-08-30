package pe.leboulevard.demo.infrastructure.genero.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.leboulevard.demo.infrastructure.genero.entity.GeneroEntity;

@Repository
public interface GeneroRepositoryJpa extends JpaRepository<GeneroEntity, Integer> {
}