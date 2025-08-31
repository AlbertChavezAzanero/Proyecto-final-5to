package pe.leboulevard.demo.presentation.clientes;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.leboulevard.demo.domain.clientes.model.ClienteModel;
import pe.leboulevard.demo.domain.clientes.service.ClienteService;
import pe.leboulevard.demo.domain.origenes.service.OrigenService;
import pe.leboulevard.demo.domain.tipodocumento.service.TipoDocumentoService;

@Controller
@RequestMapping("/clientes")
@PreAuthorize("hasAuthority('GESTIONAR_CLIENTES')")
@RequiredArgsConstructor
public class ClientesController {

    private final ClienteService clienteService;
    private final TipoDocumentoService tipoDocumentoService;
    private final OrigenService origenService;

    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "clientes";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(@RequestParam(required = false) Integer id, Model model) {
        ClienteModel cliente = id != null ? clienteService.buscarPorId(id).orElse(new ClienteModel()) : new ClienteModel();
        model.addAttribute("cliente", cliente);
        model.addAttribute("tiposDocumento", tipoDocumentoService.listarTodos());

        // --- ESTA LÍNEA ES LA CLAVE ---
        // Se asegura de que la lista de orígenes se envíe a la vista
        model.addAttribute("origenes", origenService.listarTodos());

        return "formulario-cliente";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute ClienteModel cliente, RedirectAttributes redirectAttributes) {
        clienteService.guardarCliente(cliente);
        redirectAttributes.addFlashAttribute("successMessage", "Cliente guardado exitosamente.");
        return "redirect:/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            clienteService.eliminarCliente(id);
            redirectAttributes.addFlashAttribute("successMessage", "Cliente eliminado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "No se pudo eliminar el cliente.");
        }
        return "redirect:/clientes";
    }
}