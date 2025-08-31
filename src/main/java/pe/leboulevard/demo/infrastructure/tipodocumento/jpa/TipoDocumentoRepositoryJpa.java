package pe.leboulevard.demo.infrastructure.tipodocumento.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.leboulevard.demo.infrastructure.tipodocumento.entity.TipoDocumentoEntity;

public interface TipoDocumentoRepositoryJpa extends JpaRepository<TipoDocumentoEntity, Integer> {}