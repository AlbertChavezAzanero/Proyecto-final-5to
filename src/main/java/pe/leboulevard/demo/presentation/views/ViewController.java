package pe.leboulevard.demo.presentation.views;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // <-- IMPORTANTE: Añadir import
import org.springframework.web.bind.annotation.GetMapping;
import pe.leboulevard.demo.infrastructure.estadocivil.entity.EstadoCivilEntity;
import pe.leboulevard.demo.infrastructure.estadocivil.jpa.EstadoCivilRepositoryJpa;
import pe.leboulevard.demo.infrastructure.genero.entity.GeneroEntity;
import pe.leboulevard.demo.infrastructure.genero.jpa.GeneroRepositoryJpa;

import java.util.List;

@Controller
@RequiredArgsConstructor // <-- AÑADIR ESTA ANOTACIÓN
public class ViewController {

    // ==== SECCIÓN AÑADIDA: INYECCIÓN DE REPOSITORIOS ====
    private final GeneroRepositoryJpa generoRepository;
    private final EstadoCivilRepositoryJpa estadoCivilRepository;
    // =======================================================

    /**
     * Muestra la página principal que contiene los formularios de login y registro.
     * @return el nombre del archivo HTML (sin la extensión .html)
     */
    @GetMapping("/login")
    // ==== SECCIÓN MODIFICADA: MÉTODO AHORA USA 'Model' ====
    public String loginAndRegisterPage(Model model) {
        // Obtenemos las listas desde la base de datos
        List<GeneroEntity> generos = generoRepository.findAll();
        List<EstadoCivilEntity> estadosCiviles = estadoCivilRepository.findAll();

        // Las añadimos al modelo para que Thymeleaf pueda usarlas
        model.addAttribute("generos", generos);
        model.addAttribute("estadosCiviles", estadosCiviles);

        // Esto buscará un archivo llamado login-register.html en la carpeta /templates
        return "login-register";
    }
    // ==========================================================

    /**
     * Muestra la página principal del dashboard después de iniciar sesión.
     * @return el nombre del archivo HTML del dashboard.
     */
    @GetMapping("/dashboard")
    public String dashboardPage() {
        // Esto buscará un archivo llamado dashboard.html en la carpeta /templates
        return "dashboard";
    }
}