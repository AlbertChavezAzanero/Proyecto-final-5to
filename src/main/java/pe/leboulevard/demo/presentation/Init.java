package pe.leboulevard.demo.presentation;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pe.leboulevard.demo.domain.cargos.model.CargosModel;
import pe.leboulevard.demo.domain.cargos.service.CargosService;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class Init {

    private final CargosService cargosService;

    @PostConstruct
    public void star() {
        Optional<CargosModel> cargos = cargosService.buscarPorId(1);; // Se llama al método correcto y se pasa un Integer
        if (cargos.isPresent()) {
            log.info("Cargos nombre:{}", cargos.get().getNombre());
            log.info("Cargos Descripcion:{}", cargos.get().getDescripcion());
            log.info("Cargos activo:{}", cargos.get().getActivo());

        } else {
            log.warn("No se encontró el cargo con ID 1");
        }
    }
}