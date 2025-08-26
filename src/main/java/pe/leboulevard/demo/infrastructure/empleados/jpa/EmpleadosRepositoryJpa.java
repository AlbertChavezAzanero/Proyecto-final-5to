package pe.leboulevard.demo.infrastructure.empleados.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.leboulevard.demo.infrastructure.empleados.entity.EmpleadosEntity;

public interface EmpleadosRepositoryJpa extends JpaRepository<EmpleadosEntity,Long> {
}
