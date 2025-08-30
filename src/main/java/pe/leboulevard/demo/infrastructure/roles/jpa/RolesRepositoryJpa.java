package pe.leboulevard.demo.infrastructure.roles.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.leboulevard.demo.infrastructure.roles.entity.RolesEntity;

import java.util.Optional; // Asegúrate de tener este import

public interface RolesRepositoryJpa extends JpaRepository<RolesEntity,Long> {
    // Spring Data JPA creará la consulta automáticamente para buscar por nombre.
    Optional<RolesEntity> findByNombre(String nombre);
}