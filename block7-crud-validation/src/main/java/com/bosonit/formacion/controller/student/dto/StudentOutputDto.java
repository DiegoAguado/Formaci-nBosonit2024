package com.bosonit.formacion.controller.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutputDto {
    String id_student;
    int num_hours_week;
    String coments;
    String branch;
}
