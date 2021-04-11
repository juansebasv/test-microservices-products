package co.com.producto.producto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ciudad implements Serializable {

    private int idCiudad;
    private String name;
    private String capital;
    private Date fechaFundacion;
}
