package pe.leboulevard.demo.domain.usuarios.service;

import pe.leboulevard.demo.domain.usuarios.model.UsuariosModel;

import java.util.List;
import java.util.Optional;

public interface UsuariosService {
    UsuariosModel guardarUsuario(UsuariosModel usuarioModel);
    List<UsuariosModel> listarTodos();
    Optional<UsuariosModel> buscarPorId(Integer id);
    UsuariosModel crearUsuarioDesdeAdmin(UsuariosModel usuario);

    // --- AÑADE ESTE MÉTODO ---
    UsuariosModel actualizarUsuarioDesdeAdmin(UsuariosModel usuario);
}