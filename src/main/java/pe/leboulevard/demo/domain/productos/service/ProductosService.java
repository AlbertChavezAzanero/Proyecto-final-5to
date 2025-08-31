package pe.leboulevard.demo.domain.productos.service;

import pe.leboulevard.demo.domain.productos.model.ProductosModel;
import java.util.List;
import java.util.Optional;

public interface ProductosService {
    List<ProductosModel> listarTodos();
    Optional<ProductosModel> buscarPorId(Integer id);
    ProductosModel guardarProducto(ProductosModel producto);
    void eliminarProducto(Integer id);
}