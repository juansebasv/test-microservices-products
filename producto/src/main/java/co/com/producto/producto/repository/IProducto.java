package co.com.producto.producto.repository;

import co.com.producto.producto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProducto extends JpaRepository<Producto, Integer> {
}
