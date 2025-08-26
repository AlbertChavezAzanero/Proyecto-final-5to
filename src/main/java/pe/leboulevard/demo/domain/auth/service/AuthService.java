package pe.leboulevard.demo.domain.auth.service;

import pe.leboulevard.demo.domain.auth.model.Auth;
import pe.leboulevard.demo.domain.usuarios.model.UsuariosModel;

public interface AuthService {
    /**
     * Autentica a un usuario basado en su nombre de usuario y contraseña.
     *
     * @param username El nombre de usuario.
     * @param password La contraseña en texto plano.
     * @return Un objeto Auth que contiene los tokens de acceso y refresco.
     */
    Auth login(String username, String password);

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username El nombre de usuario a buscar.
     * @return El modelo del usuario encontrado.
     */
    UsuariosModel findByUsername(String username);
}
