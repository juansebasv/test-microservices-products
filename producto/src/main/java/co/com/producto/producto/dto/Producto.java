package co.com.producto.producto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto implements Serializable {

    private int idProducto;
    private String nombre;
    private String marca;
    private Double porcentaje;
}
