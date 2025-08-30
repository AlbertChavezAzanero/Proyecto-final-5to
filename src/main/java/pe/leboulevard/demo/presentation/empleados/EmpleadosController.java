package pe.leboulevard.demo.presentation.empleados;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.leboulevard.demo.domain.cargos.service.CargosService;
import pe.leboulevard.demo.domain.common.exceptions.DuplicateDataException;
import pe.leboulevard.demo.domain.departamentos.service.DepartamentosService;
import pe.leboulevard.demo.domain.empleados.model.EmpleadosModel;
import pe.leboulevard.demo.domain.empleados.service.EmpleadosService;
import pe.leboulevard.demo.domain.estadocivil.service.EstadoCivilService;
import pe.leboulevard.demo.domain.genero.service.GeneroService;
import pe.leboulevard.demo.domain.tipodocumento.service.TipoDocumentoService;

@Controller
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadosController {

    private final EmpleadosService empleadosService;
    private final CargosService cargosService;
    private final DepartamentosService departamentosService;
    private final GeneroService generoService;
    private final EstadoCivilService estadoCivilService;
    private final TipoDocumentoService tipoDocumentoService;

    @GetMapping
    public String listarEmpleados(Model model) {
        model.addAttribute("empleados", empleadosService.listarTodosLosEmpleados());
        // --- AÑADIDO PARA EVITAR ERROR EN THYMELEAF ---
        // Se necesita un objeto 'empleado' para el título del modal en la vista principal
        if (!model.containsAttribute("empleado")) {
            model.addAttribute("empleado", new EmpleadosModel());
        }
        return "empleados";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        if (!model.containsAttribute("empleado")) {
            model.addAttribute("empleado", new EmpleadosModel());
        }
        cargarAtributosDelModelo(model);
        return "formulario-empleado";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        empleadosService.buscarPorId(id).ifPresent(empleado -> {
            model.addAttribute("empleado", empleado);
            cargarAtributosDelModelo(model);
        });
        return "formulario-empleado";
    }

    @PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute("empleado") EmpleadosModel empleado, RedirectAttributes redirectAttributes) {
        try {
            empleadosService.guardarEmpleado(empleado);
            redirectAttributes.addFlashAttribute("successMessage", "Empleado guardado exitosamente.");
            return "redirect:/empleados";
        } catch (DuplicateDataException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            // Guarda el objeto con los datos ingresados para no perderlos
            redirectAttributes.addFlashAttribute("empleado", empleado);

            if (empleado.getIdEmpleado() != null) {
                // Vuelve a la página de edición con los datos y el mensaje de error
                return "redirect:/empleados/editar/" + empleado.getIdEmpleado();
            } else {
                // Vuelve a la página de nuevo empleado con los datos y el mensaje de error
                return "redirect:/empleados/nuevo";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ocurrió un error inesperado al guardar: " + e.getMessage());
            redirectAttributes.addFlashAttribute("empleado", empleado); // También devuelve los datos
            if (empleado.getIdEmpleado() != null) {
                return "redirect:/empleados/editar/" + empleado.getIdEmpleado();
            } else {
                return "redirect:/empleados/nuevo";
            }
        }
    }


    @PostMapping("/cambiar-estado/{id}")
    public String cambiarEstado(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            empleadosService.cambiarEstadoEmpleado(id);
            redirectAttributes.addFlashAttribute("successMessage", "Estado del empleado actualizado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al cambiar el estado del empleado.");
        }
        return "redirect:/empleados";
    }

    private void cargarAtributosDelModelo(Model model) {
        model.addAttribute("cargos", cargosService.listarTodosLosCargos());
        model.addAttribute("departamentos", departamentosService.listarTodosLosDepartamentos());
        model.addAttribute("generos", generoService.listarTodos());
        model.addAttribute("estadosCiviles", estadoCivilService.listarTodos());
        model.addAttribute("tiposDocumento", tipoDocumentoService.listarTodos());
    }
}