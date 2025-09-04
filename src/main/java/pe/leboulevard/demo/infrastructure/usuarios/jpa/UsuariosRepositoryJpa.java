package pe.leboulevard.demo.infrastructure.usuarios.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.leboulevard.demo.infrastructure.usuarios.entity.UsuariosEntity;

import java.util.Optional;

public interface UsuariosRepositoryJpa extends JpaRepository<UsuariosEntity, Integer> {
    Optional<UsuariosEntity> findByUsername(String username);

    boolean existsByRolIdRol(Integer idRol);

    // --- AÑADE ESTE NUEVO MÉTODO ---
    // Spring Data JPA entenderá que debe hacer una búsqueda case-insensitive
    Optional<UsuariosEntity> findByUsernameIgnoreCase(String username);
}