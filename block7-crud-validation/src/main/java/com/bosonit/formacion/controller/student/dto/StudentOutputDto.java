package com.bosonit.formacion.controller.Student.dto;

import com.bosonit.formacion.domain.Asignatura;
import com.bosonit.formacion.domain.Persona;
import com.bosonit.formacion.domain.Profesor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutputDto {
    String id_student;
    int num_hours_week;
    String coments;
    String branch;
}
