package com.bosonit.formacion.domain;

import com.bosonit.formacion.controller.dto.PersonaInputDto;
import com.bosonit.formacion.controller.dto.PersonaOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

@Entity
@Setter
@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue
    private int id;
    private String nombre;
    private String edad;
    private String poblacion;

    public Persona(PersonaInputDto persona){
        this.id = persona.getId();
        this.nombre = persona.getNombre();
        this.edad = persona.getEdad();
        this.poblacion = persona.getPoblacion();
    }

    public PersonaOutputDto personaToPersonaOutputDto(){
        return new PersonaOutputDto(
                this.id,
                this.nombre,
                this.edad,
                this.poblacion
        );
    }
}
