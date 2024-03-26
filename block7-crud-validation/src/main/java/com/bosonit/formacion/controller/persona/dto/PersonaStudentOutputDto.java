package com.bosonit.formacion.controller.persona.dto;

import com.bosonit.formacion.domain.Persona;
import com.bosonit.formacion.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaStudentOutputDto extends PersonaOutputDto {
    String id_student;
    int num_hours_week;
    String coments;
    String branch;

    public PersonaStudentOutputDto(Persona persona, Student student) {
        super(persona.getId_persona(), persona.getUsuario(), persona.getName(), persona.getSurname(), persona.getCompany_email(), persona.getPersonal_email(),
                persona.getCity(), persona.isActive(), persona.getCreated_date(), persona.getUpdatedAt(), persona.getImagen_url(), persona.getTermination_date(), persona.isAdmin());
        this.id_student = student.getId_student();
        this.num_hours_week = student.getNum_hours_week();
        this.coments = student.getComents();
        this.branch = student.getBranch();
    }
}
