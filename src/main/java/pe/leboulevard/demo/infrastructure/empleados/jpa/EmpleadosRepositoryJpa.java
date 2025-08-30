package pe.leboulevard.demo.infrastructure.empleados.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.leboulevard.demo.infrastructure.empleados.entity.EmpleadosEntity;

import java.util.Optional; // <-- Asegúrate de tener este import

@Repository
public interface EmpleadosRepositoryJpa extends JpaRepository<EmpleadosEntity, Long> { // El ID es Long

    // --- AÑADIR ESTE MÉTODO ---
    // Spring Data JPA entenderá que quieres buscar por la columna 'numero_documento'
    Optional<EmpleadosEntity> findByNumeroDocumento(String numeroDocumento);

}