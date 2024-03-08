package com.bosonit.formacion.controller.Cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteInputDto {
    private String dni;
    private String nombre;
    private String direccion;
    private String cod_provincia;
}
