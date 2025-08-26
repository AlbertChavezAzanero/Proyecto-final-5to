package pe.leboulevard.demo.infrastructure.roles.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.leboulevard.demo.infrastructure.roles.entity.RolesEntity;

public interface RolesRepositoryJpa extends JpaRepository<RolesEntity,Long> {
}
