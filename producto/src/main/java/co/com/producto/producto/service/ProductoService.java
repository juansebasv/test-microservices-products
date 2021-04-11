package co.com.producto.producto.service;

import co.com.producto.producto.dto.Producto;
import co.com.producto.producto.repository.IProducto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private IProducto repo;

    @Autowired
    public ProductoService(IProducto repo) {
        this.repo = repo;
    }

    public void crearProdcuto(Producto producto) throws Exception {
        ModelMapper model = new ModelMapper();
        co.com.producto.producto.model.Producto aux = new co.com.producto.producto.model.Producto();

        model.map(producto, aux);
        repo.saveAndFlush(aux);
    }

    public List<Producto> listarProductos() {
        ModelMapper model = new ModelMapper();
        List<co.com.producto.producto.model.Producto> list = repo.findAll();

        List<Producto> result = list.stream().map(var -> {
            Producto aux = new Producto();
            model.map(var, aux);
            return aux;
        }).collect(Collectors.toList());

        return result;
    }

    public Producto buscarProductoById(int idProducto) throws Exception {
        ModelMapper model = new ModelMapper();
        Producto producto = new Producto();
        Optional<co.com.producto.producto.model.Producto> result = repo.findById(idProducto);

        if (!result.isPresent()) {
            return null;
        }

        model.map(result.get(), producto);
        return producto;
    }

    public Boolean actualizarProducto(int idProducto, Producto producto) throws Exception {
        ModelMapper model = new ModelMapper();
        Optional<co.com.producto.producto.model.Producto> result = repo.findById(idProducto);

        if (!result.isPresent()) {
            return false;
        }

        result.get().setNombre(producto.getNombre());
        result.get().setMarca(producto.getMarca());
        result.get().setPorcentaje(producto.getPorcentaje());
        repo.save(result.get());

        return true;
    }

    public Boolean eliminarProducto(int idProducto) throws Exception {
        Optional<co.com.producto.producto.model.Producto> result = repo.findById(idProducto);

        if (!result.isPresent()) {
            return false;
        }

        repo.deleteById(idProducto);
        return true;
    }
}
