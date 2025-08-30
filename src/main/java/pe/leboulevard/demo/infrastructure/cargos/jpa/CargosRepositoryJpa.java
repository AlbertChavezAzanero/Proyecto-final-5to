package pe.leboulevard.demo.infrastructure.cargos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.leboulevard.demo.infrastructure.cargos.entity.CargosEntity;

@Repository
public interface CargosRepositoryJpa extends JpaRepository<CargosEntity, Integer> {
    // Spring Data JPA implementará los métodos por nosotros.
}