package pe.leboulevard.demo.infrastructure.usuarios.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.leboulevard.demo.infrastructure.usuarios.entity.UsuariosEntity;

import java.util.Optional;

public interface UsuariosRepositoryJpa extends JpaRepository<UsuariosEntity, Integer> {
    Optional<UsuariosEntity> findByUsername(String username);

    // --- AÑADE ESTE MÉTODO ---
    boolean existsByRolIdRol(Integer idRol);
}