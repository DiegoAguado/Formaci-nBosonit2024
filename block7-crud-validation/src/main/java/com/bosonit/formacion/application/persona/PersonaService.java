package com.bosonit.formacion.application.persona;

import com.bosonit.formacion.controller.persona.dto.PersonaInputDto;
import com.bosonit.formacion.controller.persona.dto.PersonaOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;
import com.bosonit.formacion.customException.UnprocessableEntityException;

import java.util.List;

public interface PersonaService {
    PersonaOutputDto addPersona(PersonaInputDto persona) throws UnprocessableEntityException;
    PersonaOutputDto getPersonaById(int id, String outputType) throws EntityNotFoundException;
    List<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize);
    List<PersonaOutputDto> getAllPersonas();
    List<PersonaOutputDto> getAllPersonasByUsuarioLike(String usuario);
    PersonaOutputDto updatePersona(PersonaInputDto persona) throws EntityNotFoundException, UnprocessableEntityException;
    void deletePersonaById(int id) throws EntityNotFoundException;
}
