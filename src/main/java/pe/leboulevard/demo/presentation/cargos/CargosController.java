package pe.leboulevard.demo.presentation.cargos;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.leboulevard.demo.domain.cargos.model.CargosModel;
import pe.leboulevard.demo.domain.cargos.service.CargosService;

import java.util.Optional;

@RestController
@RequestMapping("/api/cargos") // Ruta protegida según tu SecurityConfig
@RequiredArgsConstructor
public class CargosController {

    private final CargosService cargosService;

    @PostMapping
    public ResponseEntity<CargosModel> crearCargo(@RequestBody CargosModel nuevoCargo) {
        CargosModel cargoGuardado = cargosService.guardarCargo(nuevoCargo);
        return new ResponseEntity<>(cargoGuardado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargosModel> obtenerCargoPorId(@PathVariable Integer id) {
        Optional<CargosModel> cargo = cargosService.buscarCargoPorId(id);
        return cargo.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}