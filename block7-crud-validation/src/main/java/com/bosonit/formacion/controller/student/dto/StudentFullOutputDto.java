package com.bosonit.formacion.controller.Student.dto;

import com.bosonit.formacion.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
