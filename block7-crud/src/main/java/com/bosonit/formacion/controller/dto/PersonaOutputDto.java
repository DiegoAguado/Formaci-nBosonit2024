package com.bosonit.formacion.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaOutputDto {
    int id;
    String nombre;
    String edad;
    String poblacion;
}
