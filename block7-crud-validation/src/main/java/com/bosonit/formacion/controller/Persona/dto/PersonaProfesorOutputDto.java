package com.bosonit.formacion.controller.Persona.dto;

import com.bosonit.formacion.controller.Profesor.dto.ProfesorOutputDto;
import com.bosonit.formacion.domain.Persona;
import com.bosonit.formacion.domain.Profesor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaProfesorOutputDto extends PersonaOutputDto{
    ProfesorOutputDto profesor;

    public PersonaProfesorOutputDto(Persona persona, Profesor profesor) {
        super(persona.getId_persona(), persona.getUsuario(), persona.getName(), persona.getSurname(), persona.getCompany_email(), persona.getPersonal_email(),
                persona.getCity(), persona.isActive(), persona.getCreated_date(), persona.getUpdatedAt(), persona.getImagen_url(), persona.getTermination_date());
        this.profesor = profesor.profesorToProfesorOutputDto();
    }
}
