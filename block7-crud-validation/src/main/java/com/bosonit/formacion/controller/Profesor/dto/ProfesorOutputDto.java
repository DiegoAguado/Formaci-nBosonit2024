package com.bosonit.formacion.controller.Profesor.dto;

import com.bosonit.formacion.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorOutputDto {
    private String id_profesor;
    private String coments;
    private String branch;
}
