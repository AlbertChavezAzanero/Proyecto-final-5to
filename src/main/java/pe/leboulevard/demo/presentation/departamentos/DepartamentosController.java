package pe.leboulevard.demo.presentation.departamentos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // Importar
import pe.leboulevard.demo.domain.departamentos.model.DepartamentosModel;
import pe.leboulevard.demo.domain.departamentos.service.DepartamentosService;

@Controller
@RequestMapping("/departamentos")
@RequiredArgsConstructor
public class DepartamentosController {

    private final DepartamentosService departamentosService;

    @GetMapping
    public String listarDepartamentos(Model model) {
        model.addAttribute("departamentos", departamentosService.listarTodosLosDepartamentos());
        return "departamentos";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("departamento", new DepartamentosModel());
        return "formulario-departamento";
    }

    @PostMapping("/guardar")
    public String guardarDepartamento(@ModelAttribute DepartamentosModel departamento, RedirectAttributes redirectAttributes) {
        try {
            departamentosService.guardarDepartamento(departamento);
            redirectAttributes.addFlashAttribute("successMessage", "Departamento guardado exitosamente.");
            return "redirect:/departamentos";
        } catch (Exception e) {
            // Capturamos cualquier error y lo mostramos en el formulario
            redirectAttributes.addFlashAttribute("errorMessage", "Error al guardar: " + e.getMessage());
            redirectAttributes.addFlashAttribute("departamento", departamento); // Devolvemos los datos ingresados
            if (departamento.getIdDepartamento() != null) {
                return "redirect:/departamentos/editar/" + departamento.getIdDepartamento();
            }
            return "redirect:/departamentos/nuevo";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        departamentosService.buscarPorId(id).ifPresent(depto -> model.addAttribute("departamento", depto));
        return "formulario-departamento";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDepartamento(@PathVariable Integer id) {
        departamentosService.eliminarDepartamento(id);
        return "redirect:/departamentos";
    }
}