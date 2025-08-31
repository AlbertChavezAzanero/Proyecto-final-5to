package pe.leboulevard.demo.application.productos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.leboulevard.demo.domain.productos.model.ProductosModel;
import pe.leboulevard.demo.domain.productos.service.ProductosService;
import pe.leboulevard.demo.infrastructure.productos.entity.ProductosEntity;
import pe.leboulevard.demo.infrastructure.productos.jpa.ProductosRepositoryJpa;
import pe.leboulevard.demo.infrastructure.productos.mapper.ProductosMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductosServiceImpl implements ProductosService {

    private final ProductosRepositoryJpa productosRepository;
    private final ProductosMapper productosMapper;

    @Override
    public List<ProductosModel> listarTodos() {
        return productosRepository.findAll().stream()
                .map(productosMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductosModel> buscarPorId(Integer id) {
        return productosRepository.findById(id).map(productosMapper::toModel);
    }

    @Override
    public ProductosModel guardarProducto(ProductosModel producto) {
        ProductosEntity entity = productosMapper.toEntity(producto);

        // --- LÓGICA DE AUDITORÍA AÑADIDA ---
        if (entity.getIdProducto() == null) {
            // Si es un producto nuevo, asignamos ambos usuarios.
            entity.setUsuarioCreacion("admin-system");
            entity.setUsuarioActualizacion("admin-system");
        } else {
            // Si es una edición, preservamos los datos de creación originales.
            productosRepository.findById(entity.getIdProducto()).ifPresent(original -> {
                entity.setUsuarioCreacion(original.getUsuarioCreacion());
                entity.setFechaCreacion(original.getFechaCreacion());
            });
            entity.setUsuarioActualizacion("admin-system-update");
        }

        ProductosEntity savedEntity = productosRepository.save(entity);
        return productosMapper.toModel(savedEntity);
    }

    @Override
    public void eliminarProducto(Integer id) {
        productosRepository.deleteById(id);
    }
}