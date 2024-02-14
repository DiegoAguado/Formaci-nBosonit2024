package com.bosonit.formacion.application;

import com.bosonit.formacion.controller.dto.PersonaInputDto;
import com.bosonit.formacion.controller.dto.PersonaOutputDto;

import java.util.List;

public interface PersonaService {
    PersonaOutputDto addPersona(PersonaInputDto persona) throws Exception;
    PersonaOutputDto getPersonaById(int id);
    List<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize);
    List<PersonaOutputDto> getAllPersonasByUsuarioLike(String usuario);
    PersonaOutputDto updatePersona(PersonaInputDto persona) throws Exception;
    void deletePersonaById(int id);
}
