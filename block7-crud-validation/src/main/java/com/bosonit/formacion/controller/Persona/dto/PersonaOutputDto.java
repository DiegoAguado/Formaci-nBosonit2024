package com.bosonit.formacion.controller.Persona.dto;

import com.bosonit.formacion.controller.Profesor.dto.ProfesorOutputDto;
import com.bosonit.formacion.controller.Student.dto.StudentOutputDto;
import com.bosonit.formacion.domain.Profesor;
import com.bosonit.formacion.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaOutputDto {
    int id_persona;
    String usuario;
    String name;
    String surname;
    String company_email;
    String personal_email;
    String city;
    boolean active;
    Date created_date;
    Date updatedAt;
    String imagen_url;
    Date termination_date;
}
