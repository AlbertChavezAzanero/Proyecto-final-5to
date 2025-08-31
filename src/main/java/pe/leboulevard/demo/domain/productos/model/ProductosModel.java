package pe.leboulevard.demo.domain.productos.model;

import lombok.Getter;
import lombok.Setter;
import pe.leboulevard.demo.domain.categoriasproducto.model.CategoriasProductoModel;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductosModel {
    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private Integer stockActual;
    private BigDecimal precioUnitario;
    private CategoriasProductoModel categoria;
}