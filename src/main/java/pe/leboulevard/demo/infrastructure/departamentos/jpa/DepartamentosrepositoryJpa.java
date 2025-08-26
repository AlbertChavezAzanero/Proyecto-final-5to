package pe.leboulevard.demo.infrastructure.departamentos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.leboulevard.demo.infrastructure.departamentos.entity.DepartamentosEntity;

public interface DepartamentosrepositoryJpa extends JpaRepository<DepartamentosEntity,Long> {
}
