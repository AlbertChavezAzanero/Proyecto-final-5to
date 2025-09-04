package pe.leboulevard.demo.application.cotizaciones;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.leboulevard.demo.domain.cotizaciones.model.CotizacionModel;
import pe.leboulevard.demo.domain.cotizaciones.service.CotizacionService;
import pe.leboulevard.demo.infrastructure.cotizaciones.entity.CotizacionEntity;
import pe.leboulevard.demo.infrastructure.cotizaciones.jpa.CotizacionRepositoryJpa;
import pe.leboulevard.demo.infrastructure.cotizaciones.mapper.CotizacionMapper;
import pe.leboulevard.demo.infrastructure.usuarios.entity.UsuariosEntity;
import pe.leboulevard.demo.infrastructure.usuarios.jpa.UsuariosRepositoryJpa;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CotizacionServiceImpl implements CotizacionService {

    private final CotizacionRepositoryJpa cotizacionRepository;
    private final CotizacionMapper cotizacionMapper;
    private final UsuariosRepositoryJpa usuariosRepository;

    @Override
    public List<CotizacionModel> listarTodas() {
        return cotizacionRepository.findAll().stream().map(cotizacionMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Optional<CotizacionModel> buscarPorId(Integer id) {
        return cotizacionRepository.findById(id).map(cotizacionMapper::toModel);
    }

    @Override
    @Transactional
    public CotizacionModel guardarCotizacion(CotizacionModel cotizacionModel) {
        // --- LÓGICA DE GUARDADO MEJORADA ---

        // 1. Validar que la cotización tenga detalles
        if (cotizacionModel.getDetalles() == null || cotizacionModel.getDetalles().isEmpty()) {
            throw new IllegalStateException("No se puede guardar una cotización sin productos en el detalle.");
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UsuariosEntity usuarioEntity = usuariosRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        CotizacionEntity cotizacionEntity = cotizacionMapper.toEntity(cotizacionModel);

        cotizacionEntity.setUsuario(usuarioEntity);
        cotizacionEntity.setUsuarioCreacion(username);
        cotizacionEntity.setUsuarioActualizacion(username);

        // 2. Asignar estado inicial si es una nueva cotización
        if (cotizacionEntity.getIdCotizacion() == null) {
            cotizacionEntity.setEstado("pendiente");
        }

        BigDecimal total = cotizacionEntity.getDetalles().stream()
                .map(detalle -> detalle.getPrecioUnitario().multiply(new BigDecimal(detalle.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cotizacionEntity.setTotal(total);

        cotizacionEntity.getDetalles().forEach(detalle -> {
            detalle.setCotizacion(cotizacionEntity);
            detalle.setUsuarioCreacion(username);
            detalle.setUsuarioActualizacion(username);
        });

        CotizacionEntity savedEntity = cotizacionRepository.save(cotizacionEntity);

        return cotizacionMapper.toModel(savedEntity);
    }

    @Override
    public void eliminarCotizacion(Integer id) {
        cotizacionRepository.deleteById(id);
    }
}