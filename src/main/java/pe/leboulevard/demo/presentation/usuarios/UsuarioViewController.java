package pe.leboulevard.demo.presentation.usuarios;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.leboulevard.demo.domain.usuarios.model.UsuariosModel;
import pe.leboulevard.demo.domain.usuarios.service.UsuariosService;
import pe.leboulevard.demo.infrastructure.empleados.entity.EmpleadosEntity;
import pe.leboulevard.demo.infrastructure.empleados.jpa.EmpleadosRepositoryJpa;
import pe.leboulevard.demo.infrastructure.roles.jpa.RolesRepositoryJpa;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/usuarios")
@PreAuthorize("hasRole('Administrador')")
@RequiredArgsConstructor
public class UsuarioViewController {

    private final UsuariosService usuariosService;
    private final RolesRepositoryJpa rolesRepositoryJpa;
    private final EmpleadosRepositoryJpa empleadosRepositoryJpa;

    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuariosService.listarTodos());
        return "usuarios";
    }

    // --- MÉTODO UNIFICADO PARA MOSTRAR EL FORMULARIO ---
    @GetMapping("/formulario")
    public String mostrarFormulario(@RequestParam(required = false) Integer id, Model model) {
        UsuariosModel usuario;
        if (id != null) {
            // --- MODO EDICIÓN ---
            usuario = usuariosService.buscarPorId(id)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        } else {
            // --- MODO CREACIÓN ---
            usuario = new UsuariosModel();

            // Para el modo creación, necesitamos la lista de empleados sin usuario
            List<EmpleadosEntity> todosLosEmpleados = empleadosRepositoryJpa.findAll();
            List<UsuariosModel> todosLosUsuarios = usuariosService.listarTodos();
            Set<Integer> empleadosConUsuarioIds = todosLosUsuarios.stream()
                    .map(u -> u.getEmpleado().getIdEmpleado())
                    .collect(Collectors.toSet());
            List<EmpleadosEntity> empleadosSinUsuario = todosLosEmpleados.stream()
                    .filter(empleado -> !empleadosConUsuarioIds.contains(empleado.getIdEmpleado()))
                    .collect(Collectors.toList());
            model.addAttribute("empleados", empleadosSinUsuario);
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", rolesRepositoryJpa.findAll());
        return "formulario-usuario"; // Apuntamos a un único archivo HTML
    }

    @PostMapping("/crear")
    public String crearUsuario(@ModelAttribute("usuario") UsuariosModel usuario, RedirectAttributes redirectAttributes) {
        try {
            usuariosService.crearUsuarioDesdeAdmin(usuario);
            redirectAttributes.addFlashAttribute("successMessage", "¡Usuario creado exitosamente!");
            return "redirect:/usuarios";
        } catch (DataIntegrityViolationException e) {
            String causa = e.getMostSpecificCause().getMessage();
            String mensajeAmigable = causa.contains("usuarios.username")
                    ? "El nombre de usuario '" + usuario.getUsername() + "' ya existe."
                    : "El email '" + usuario.getEmail() + "' ya está registrado.";
            redirectAttributes.addFlashAttribute("errorMessage", mensajeAmigable);
            redirectAttributes.addFlashAttribute("usuario", usuario);
            return "redirect:/usuarios/formulario"; // Redirigimos al formulario unificado
        }
    }

    @PostMapping("/actualizar")
    public String actualizarUsuario(@ModelAttribute("usuario") UsuariosModel usuario, RedirectAttributes redirectAttributes) {
        try {
            usuariosService.actualizarUsuarioDesdeAdmin(usuario);
            redirectAttributes.addFlashAttribute("successMessage", "¡Usuario actualizado exitosamente!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al actualizar el usuario: " + e.getMessage());
        }
        return "redirect:/usuarios";
    }
}