package co.com.producto.producto.controller;

import co.com.producto.producto.dto.Ciudad;
import co.com.producto.producto.dto.Producto;
import co.com.producto.producto.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @Autowired
    private final RestTemplate restTemplate;

    @PostMapping("/servicio1")
    public ResponseEntity<List<Ciudad>> consumirServicioCiudades(@RequestParam(value = "ciudad") String ciudad) {
        try {
            String url = "http://ciudades.service/juan/ciudadesName/" + ciudad;
            ResponseEntity<Ciudad[]> listaCiudades = restTemplate.getForEntity(url, Ciudad[].class);
            List<Ciudad> result = Arrays.asList(listaCiudades.getBody());

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/servicio2")
    public ResponseEntity<Ciudad> consumirServicio(@RequestParam(value = "id") String id) {
        try {
            String url = "http://ciudades.service/juan/ciudades/" + id;
            ResponseEntity<Ciudad> ciudad = restTemplate.getForEntity(url, Ciudad.class);
            Ciudad aux = ciudad.getBody();

            return new ResponseEntity<>(aux, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Producto> crearProducto(@RequestBody(required = true) Producto producto) {
        try {
            service.crearProdcuto(producto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> listarProductos() {
        List<Producto> list = service.listarProductos();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> buscarProductoById(@PathVariable("id") int idProducto) {
        try {
            Producto producto = service.buscarProductoById(idProducto);
            if (null == producto) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(producto, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable("id") int idProducto, @RequestBody(required = true) Producto producto) {
        try {
            boolean flag = service.actualizarProducto(idProducto, producto);
            if (!flag) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Producto> eliminarProducto(@PathVariable("id") int idProducto) {
        try {
            boolean flag = service.eliminarProducto(idProducto);
            if (!flag) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
