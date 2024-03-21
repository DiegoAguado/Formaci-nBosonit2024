package com.bosonit.formacion.controller.asignatura.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaInputDto {
    String id_asignatura;
    String asignatura;
    String coments;
    Date initial_date;
    Date finish_date;
}
