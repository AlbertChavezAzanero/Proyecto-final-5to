package pe.leboulevard.demo.infrastructure.permisos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.leboulevard.demo.infrastructure.permisos.entity.PermisosEntity;
import java.util.List;

public interface PermisosRepositoryJpa extends JpaRepository<PermisosEntity, Long> {
    // Sobrescribimos el método para aceptar una lista de Integer directamente si lo deseamos
    // o simplemente convertimos el tipo en el servicio.
    // Por simplicidad, lo haremos en el servicio.
}