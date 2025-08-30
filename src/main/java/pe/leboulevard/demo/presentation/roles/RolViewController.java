package pe.leboulevard.demo.presentation.roles;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.leboulevard.demo.domain.roles.model.RolesModel;
import pe.leboulevard.demo.domain.roles.service.RolesService;

@Controller
@RequestMapping("/roles")
@PreAuthorize("hasRole('Administrador')")
@RequiredArgsConstructor
public class RolViewController {

    private final RolesService rolesService;

    @GetMapping
    public String listarRoles(Model model) {
        model.addAttribute("roles", rolesService.listarTodos());
        return "roles";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(@RequestParam(required = false) Integer id, Model model) {
        RolesModel rol = id != null ?
                rolesService.buscarPorId(id).orElse(new RolesModel()) : new RolesModel();
        model.addAttribute("rol", rol);
        return "formulario-rol";
    }

    @PostMapping("/guardar")
    public String guardarRol(@ModelAttribute RolesModel rol, RedirectAttributes redirectAttributes) {
        rolesService.guardar(rol);
        redirectAttributes.addFlashAttribute("successMessage", "Rol guardado exitosamente.");
        return "redirect:/roles";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarRol(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            rolesService.eliminar(id);
            redirectAttributes.addFlashAttribute("successMessage", "Rol eliminado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/roles";
    }
}