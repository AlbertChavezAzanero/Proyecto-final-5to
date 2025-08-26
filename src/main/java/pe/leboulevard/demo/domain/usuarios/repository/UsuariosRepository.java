package pe.leboulevard.demo.domain.usuarios.repository;

import pe.leboulevard.demo.domain.usuarios.model.UsuariosModel;
import java.util.Optional;

public interface UsuariosRepository {
    Optional<UsuariosModel> findByUsername(String username);
}
