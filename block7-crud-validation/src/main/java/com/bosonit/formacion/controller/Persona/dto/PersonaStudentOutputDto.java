package com.bosonit.formacion.controller.Persona.dto;

import com.bosonit.formacion.controller.Student.dto.StudentOutputDto;
import com.bosonit.formacion.domain.Persona;
import com.bosonit.formacion.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaStudentOutputDto extends PersonaOutputDto{
    StudentOutputDto student;

    public PersonaStudentOutputDto(Persona persona, Student student) {
        super(persona.getId_persona(), persona.getUsuario(), persona.getName(), persona.getSurname(), persona.getCompany_email(), persona.getPersonal_email(),
                persona.getCity(), persona.isActive(), persona.getCreated_date(), persona.getUpdatedAt(), persona.getImagen_url(), persona.getTermination_date());
        this.student = student.studentToStudentOutputDto();
    }
}
