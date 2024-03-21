package com.bosonit.formacion.controller.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInputDto {
    String id_student;
    int id_persona;
    int num_hours_week;
    String coments;
    //String id_profesor;
    String branch;
}
