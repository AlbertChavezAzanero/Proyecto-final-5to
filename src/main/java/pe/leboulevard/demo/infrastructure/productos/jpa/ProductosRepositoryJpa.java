package pe.leboulevard.demo.infrastructure.productos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.leboulevard.demo.infrastructure.productos.entity.ProductosEntity;

@Repository
public interface ProductosRepositoryJpa extends JpaRepository<ProductosEntity, Integer> {
}