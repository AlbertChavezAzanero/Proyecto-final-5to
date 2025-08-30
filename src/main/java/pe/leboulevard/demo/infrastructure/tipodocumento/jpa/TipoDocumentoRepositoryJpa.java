package pe.leboulevard.demo.infrastructure.tipodocumento.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.leboulevard.demo.infrastructure.tipodocumento.entity.TipoDocumentoEntity;

@Repository
public interface TipoDocumentoRepositoryJpa extends JpaRepository<TipoDocumentoEntity, Integer> {
}