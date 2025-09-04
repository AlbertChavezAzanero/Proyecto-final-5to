package pe.leboulevard.demo.domain.cotizaciones.service;

import pe.leboulevard.demo.domain.cotizaciones.model.CotizacionModel;
import java.util.List;
import java.util.Optional;

public interface CotizacionService {
    List<CotizacionModel> listarTodas();
    Optional<CotizacionModel> buscarPorId(Integer id);
    CotizacionModel guardarCotizacion(CotizacionModel cotizacion);
    void eliminarCotizacion(Integer id);
}