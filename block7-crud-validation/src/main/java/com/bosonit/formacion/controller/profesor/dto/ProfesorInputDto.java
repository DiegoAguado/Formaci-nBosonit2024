package com.bosonit.formacion.controller.Profesor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorInputDto {
    private String id_profesor;
    private int id_persona;
    private String coments;
    private String branch;
}
