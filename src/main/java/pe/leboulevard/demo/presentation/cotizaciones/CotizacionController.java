package pe.leboulevard.demo.presentation.cotizaciones;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.leboulevard.demo.domain.clientes.service.ClienteService;
import pe.leboulevard.demo.domain.cotizaciones.model.CotizacionModel;
import pe.leboulevard.demo.domain.cotizaciones.service.CotizacionService;
import pe.leboulevard.demo.domain.productos.service.ProductosService;

@Controller
@RequestMapping("/cotizaciones")
@PreAuthorize("hasAuthority('GESTIONAR_COTIZACIONES')")
@RequiredArgsConstructor
public class CotizacionController {

    private final CotizacionService cotizacionService;
    private final ClienteService clienteService;
    private final ProductosService productosService;

    @GetMapping
    public String listarCotizaciones(Model model) {
        model.addAttribute("cotizaciones", cotizacionService.listarTodas());
        return "cotizaciones";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cotizacion", new CotizacionModel());
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("productos", productosService.listarTodos());
        return "formulario-cotizacion";
    }

    @PostMapping("/guardar")
    public String guardarCotizacion(@ModelAttribute CotizacionModel cotizacion, RedirectAttributes redirectAttributes) {
        try {
            cotizacionService.guardarCotizacion(cotizacion);
            redirectAttributes.addFlashAttribute("successMessage", "Cotización guardada exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al guardar la cotización: " + e.getMessage());
        }
        return "redirect:/cotizaciones";
    }

    // --- MÉTODO NUEVO PARA VER EL DETALLE ---
    @GetMapping("/{id}")
    public String verDetalleCotizacion(@PathVariable Integer id, Model model) {
        CotizacionModel cotizacion = cotizacionService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cotización no encontrada"));
        model.addAttribute("cotizacion", cotizacion);
        return "detalle-cotizacion";
    }
}