package pe.leboulevard.demo.domain.clientes.model;

import lombok.Getter;
import lombok.Setter;
import pe.leboulevard.demo.domain.origenes.model.OrigenModel;
import pe.leboulevard.demo.domain.tipodocumento.model.TipoDocumentoModel;

@Getter
@Setter
public class ClienteModel {
    private Integer idCliente;
    private String nombre;
    private String numeroDocumento;
    private String direccion;
    private String celular;
    private String email;
    private OrigenModel origen;
    private TipoDocumentoModel tipoDocumento;
}