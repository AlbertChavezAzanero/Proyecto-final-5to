package pe.leboulevard.demo.domain.detallecotizacion.model;

import lombok.Getter;
import lombok.Setter;
import pe.leboulevard.demo.domain.productos.model.ProductosModel;
import java.math.BigDecimal;

@Getter
@Setter
public class DetalleCotizacionModel {
    private Integer idDetalleCotizacion;
    private ProductosModel producto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
}