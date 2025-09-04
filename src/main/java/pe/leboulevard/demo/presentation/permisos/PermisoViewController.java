package pe.leboulevard.demo.presentation.permisos;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.leboulevard.demo.domain.permisos.service.PermisosService;
import pe.leboulevard.demo.domain.roles.model.RolesModel;

import java.util.List;

@Controller
@RequestMapping("/permisos")
// ===== LÍNEA CORREGIDA: Volvemos a usar hasRole =====
@PreAuthorize("hasRole('Administrador')")
@RequiredArgsConstructor
public class PermisoViewController {

    private final PermisosService permisosService;

    @GetMapping
    public String mostrarListaDeRoles(Model model) {
        model.addAttribute("roles", permisosService.listarTodosLosRoles());
        return "permisos";
    }

    @GetMapping("/rol/{idRol}")
    public String mostrarFormularioDePermisos(@PathVariable Integer idRol, Model model) {
        RolesModel rol = permisosService.buscarRolPorId(idRol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        model.addAttribute("rol", rol);
        model.addAttribute("todosLosPermisos", permisosService.listarTodosLosPermisos());
        return "formulario-permisos";
    }

    @PostMapping("/rol/{idRol}/guardar")
    public String guardarPermisosParaRol(@PathVariable Integer idRol,
                                         @RequestParam(name = "permisosIds", required = false) List<Integer> permisosIds,
                                         RedirectAttributes redirectAttributes) {
        permisosService.actualizarPermisosDeRol(idRol, permisosIds);
        redirectAttributes.addFlashAttribute("successMessage", "¡Permisos actualizados correctamente!");
        return "redirect:/permisos";
    }
}