package pe.leboulevard.demo.infrastructure.categoriasproducto.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.leboulevard.demo.infrastructure.categoriasproducto.entity.CategoriasProductoEntity;

@Repository
public interface CategoriasProductoRepositoryJpa extends JpaRepository<CategoriasProductoEntity, Integer> {
}