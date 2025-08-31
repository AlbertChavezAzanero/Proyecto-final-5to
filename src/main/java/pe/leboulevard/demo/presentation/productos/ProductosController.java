package pe.leboulevard.demo.presentation.productos;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.leboulevard.demo.domain.categoriasproducto.service.CategoriasProductoService;
import pe.leboulevard.demo.domain.productos.model.ProductosModel;
import pe.leboulevard.demo.domain.productos.service.ProductosService;

@Controller
@RequestMapping("/productos")
@PreAuthorize("hasAuthority('GESTIONAR_PRODUCTOS')") // Usará un nuevo permiso
@RequiredArgsConstructor
public class ProductosController {

    private final ProductosService productosService;
    private final CategoriasProductoService categoriasService;

    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productosService.listarTodos());
        return "productos";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(@RequestParam(required = false) Integer id, Model model) {
        ProductosModel producto = id != null ?
                productosService.buscarPorId(id).orElse(new ProductosModel()) : new ProductosModel();
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriasService.listarTodas());
        return "formulario-producto";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute ProductosModel producto, RedirectAttributes redirectAttributes) {
        try {
            productosService.guardarProducto(producto);
            redirectAttributes.addFlashAttribute("successMessage", "Producto guardado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al guardar el producto.");
        }
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            productosService.eliminarProducto(id);
            redirectAttributes.addFlashAttribute("successMessage", "Producto eliminado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "No se pudo eliminar el producto, podría estar en uso.");
        }
        return "redirect:/productos";
    }
}