package pe.leboulevard.demo.domain.empleados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.leboulevard.demo.infrastructure.empleados.entity.EmpleadosEntity;

public interface EmpleadosRepository extends JpaRepository<EmpleadosEntity,Integer> {
}
