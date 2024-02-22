package com.bosonit.formacion.controller.Asignatura.dto;

import com.bosonit.formacion.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaOutputDto {
    String id_asignatura;
    String asignatura;
    String coments;
    Date initial_dalte;
    Date finish_date;
}
