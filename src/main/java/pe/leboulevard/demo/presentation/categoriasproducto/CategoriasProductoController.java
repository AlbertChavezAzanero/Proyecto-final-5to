package pe.leboulevard.demo.presentation.categoriasproducto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.leboulevard.demo.domain.categoriasproducto.model.CategoriasProductoModel;
import pe.leboulevard.demo.domain.categoriasproducto.service.CategoriasProductoService;

@Controller
@RequestMapping("/categorias-producto")
@PreAuthorize("hasAuthority('GESTIONAR_PRODUCTOS')") // Reutilizamos el permiso de productos
@RequiredArgsConstructor
public class CategoriasProductoController {

    private final CategoriasProductoService categoriaService;

    @GetMapping
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "categorias-producto";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(@RequestParam(required = false) Integer id, Model model) {
        CategoriasProductoModel categoria = id != null ?
                categoriaService.buscarPorId(id).orElse(new CategoriasProductoModel()) : new CategoriasProductoModel();
        model.addAttribute("categoria", categoria);
        return "formulario-categoria-producto";
    }

    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute CategoriasProductoModel categoria, RedirectAttributes redirectAttributes) {
        categoriaService.guardarCategoria(categoria);
        redirectAttributes.addFlashAttribute("successMessage", "Categoría guardada exitosamente.");
        return "redirect:/categorias-producto";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            categoriaService.eliminarCategoria(id);
            redirectAttributes.addFlashAttribute("successMessage", "Categoría eliminada correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "No se pudo eliminar la categoría, podría estar en uso.");
        }
        return "redirect:/categorias-producto";
    }
}