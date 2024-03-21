package com.bosonit.formacion.controller.student.dto;

import com.bosonit.formacion.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentFullOutputDto {
    String id_student;
    int num_hours_week;
    String comments;
    String branch;
    Persona persona;
}
