package pe.leboulevard.demo.domain.categoriasproducto.service;

import pe.leboulevard.demo.domain.categoriasproducto.model.CategoriasProductoModel;
import java.util.List;
import java.util.Optional;

public interface CategoriasProductoService {
    List<CategoriasProductoModel> listarTodas();

    // --- MÉTODOS AÑADIDOS ---
    Optional<CategoriasProductoModel> buscarPorId(Integer id);
    CategoriasProductoModel guardarCategoria(CategoriasProductoModel categoria);
    void eliminarCategoria(Integer id);
}