package com.bosonit.formacion.application;

import com.bosonit.formacion.controller.dto.PersonaInputDto;
import com.bosonit.formacion.controller.dto.PersonaOutputDto;

import java.util.List;

public interface PersonaService {
    PersonaOutputDto addPersona(PersonaInputDto persona);
    PersonaOutputDto updatePersona(PersonaInputDto persona);
    void deletePersonaById(int id);
    PersonaOutputDto getPersonaById(int id);
    List<PersonaOutputDto> getPersonasByName(String nombre);
    List<PersonaOutputDto> getAllPersonas();
}
