package pe.leboulevard.demo.infrastructure.cotizaciones.jpa;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.leboulevard.demo.infrastructure.cotizaciones.entity.CotizacionEntity;
public interface CotizacionRepositoryJpa extends JpaRepository<CotizacionEntity, Integer> {}