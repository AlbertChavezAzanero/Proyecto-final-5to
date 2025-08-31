package pe.leboulevard.demo.application.categoriasproducto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.leboulevard.demo.domain.categoriasproducto.model.CategoriasProductoModel;
import pe.leboulevard.demo.domain.categoriasproducto.service.CategoriasProductoService;
import pe.leboulevard.demo.infrastructure.categoriasproducto.entity.CategoriasProductoEntity;
import pe.leboulevard.demo.infrastructure.categoriasproducto.jpa.CategoriasProductoRepositoryJpa;
import pe.leboulevard.demo.infrastructure.categoriasproducto.mapper.CategoriasProductoMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriasProductoServiceImpl implements CategoriasProductoService {

    private final CategoriasProductoRepositoryJpa categoriasRepository;
    private final CategoriasProductoMapper categoriasMapper;

    @Override
    public List<CategoriasProductoModel> listarTodas() {
        return categoriasRepository.findAll().stream()
                .map(categoriasMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoriasProductoModel> buscarPorId(Integer id) {
        return categoriasRepository.findById(id).map(categoriasMapper::toModel);
    }

    @Override
    public CategoriasProductoModel guardarCategoria(CategoriasProductoModel categoria) {
        CategoriasProductoEntity entity = categoriasMapper.toEntity(categoria);

        // --- LÓGICA DE AUDITORÍA CORREGIDA ---
        if (entity.getIdCategoria() == null) {
            // Si es una categoría nueva, asignamos ambos usuarios.
            entity.setUsuarioCreacion("admin-system");
            entity.setUsuarioActualizacion("admin-system");
        } else {
            // Si es una edición, solo asignamos el de actualización
            // y preservamos el de creación original.
            categoriasRepository.findById(entity.getIdCategoria()).ifPresent(original -> {
                entity.setUsuarioCreacion(original.getUsuarioCreacion());
                entity.setFechaCreacion(original.getFechaCreacion());
            });
            entity.setUsuarioActualizacion("admin-system-update");
        }

        CategoriasProductoEntity savedEntity = categoriasRepository.save(entity);
        return categoriasMapper.toModel(savedEntity);
    }

    @Override
    public void eliminarCategoria(Integer id) {
        categoriasRepository.deleteById(id);
    }
}