package co.com.producto.producto.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Producto", schema = "public")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idProducto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;
    @Size(min = 0, max = 100)
    @NotNull
    @Column(name = "nombre")
    private String nombre;
    @NotNull
    @Column(name = "marca")
    private String marca;
    private Double porcentaje;
}
