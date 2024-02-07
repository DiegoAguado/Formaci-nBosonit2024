package com.bosonit.formacion.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Persona {
    private String nombre;
    private String poblacion;
    private int edad;
}
