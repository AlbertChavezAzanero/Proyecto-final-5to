package pe.leboulevard.demo.infrastructure.departamentos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.leboulevard.demo.infrastructure.departamentos.entity.DepartamentosEntity;

@Repository
public interface DepartamentosRepositoryJpa extends JpaRepository<DepartamentosEntity, Integer> {
    // Con solo extender JpaRepository, Spring Data JPA crea
    // automáticamente los métodos como findAll(), findById(), save(), etc.
}