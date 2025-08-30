package pe.leboulevard.demo.presentation.cargos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.leboulevard.demo.domain.cargos.model.CargosModel;
import pe.leboulevard.demo.domain.cargos.service.CargosService;
import pe.leboulevard.demo.domain.departamentos.service.DepartamentosService;

@Controller
@RequestMapping("/cargos")
@RequiredArgsConstructor
public class CargoViewController {

    private final CargosService cargosService;
    private final DepartamentosService departamentosService;

    @GetMapping
    public String listarCargos(Model model) {
        // <-- ¡ESTA LÍNEA ES LA MÁS IMPORTANTE!
        // Si esta línea falta, la tabla no tendrá datos para mostrarse.
        model.addAttribute("cargos", cargosService.listarTodosLosCargos());

        return "cargos";
    }

    // ... (El resto de métodos: /nuevo, /editar, /guardar, etc. se quedan como están)

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("cargo", new CargosModel());
        model.addAttribute("departamentos", departamentosService.listarTodosLosDepartamentos());
        return "formulario-cargo";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        cargosService.buscarPorId(id).ifPresent(cargo -> {
            model.addAttribute("cargo", cargo);
            model.addAttribute("departamentos", departamentosService.listarTodosLosDepartamentos());
        });
        return "formulario-cargo";
    }

    @PostMapping("/guardar")
    public String guardarCargo(@ModelAttribute CargosModel cargo, RedirectAttributes redirectAttributes) {
        try {
            cargosService.guardarCargo(cargo);
            redirectAttributes.addFlashAttribute("successMessage", "Cargo guardado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al guardar el cargo. Verifique los datos.");
        }
        return "redirect:/cargos";
    }

    @PostMapping("/cambiar-estado/{id}")
    public String cambiarEstado(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            cargosService.cambiarEstadoCargo(id);
            redirectAttributes.addFlashAttribute("successMessage", "Estado del cargo actualizado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al cambiar el estado del cargo.");
        }
        return "redirect:/cargos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCargo(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            cargosService.eliminarCargo(id);
            redirectAttributes.addFlashAttribute("successMessage", "Cargo eliminado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "No se pudo eliminar el cargo, puede estar en uso.");
        }
        return "redirect:/cargos";
    }
}