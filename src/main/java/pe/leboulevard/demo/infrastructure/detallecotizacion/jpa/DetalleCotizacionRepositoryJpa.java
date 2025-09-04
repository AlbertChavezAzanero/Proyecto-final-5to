package pe.leboulevard.demo.infrastructure.detallecotizacion.jpa;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.leboulevard.demo.infrastructure.detallecotizacion.entity.DetalleCotizacionEntity;
public interface DetalleCotizacionRepositoryJpa extends JpaRepository<DetalleCotizacionEntity, Integer> {}