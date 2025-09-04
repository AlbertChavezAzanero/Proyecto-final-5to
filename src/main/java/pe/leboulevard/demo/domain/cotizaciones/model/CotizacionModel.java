package pe.leboulevard.demo.domain.cotizaciones.model;

import lombok.Getter;
import lombok.Setter;
import pe.leboulevard.demo.domain.clientes.model.ClienteModel;
import pe.leboulevard.demo.domain.detallecotizacion.model.DetalleCotizacionModel;
import pe.leboulevard.demo.domain.usuarios.model.UsuariosModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList; // Importa ArrayList
import java.util.List;

@Getter
@Setter
public class CotizacionModel {
    private Integer idCotizacion;
    private ClienteModel cliente;
    private UsuariosModel usuario;
    private LocalDate fechaEvento;
    private LocalDate fechaDevolucion;
    private String direccionEvento;
    private String estado;
    private BigDecimal total;
    private String observaciones;

    // Inicializamos la lista para que nunca sea nula
    private List<DetalleCotizacionModel> detalles = new ArrayList<>();
}