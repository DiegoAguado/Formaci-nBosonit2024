package com.bosonit.formacion.application;

import com.bosonit.formacion.controller.dto.PersonaInputDto;
import com.bosonit.formacion.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.domain.Persona;
import com.bosonit.formacion.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService{
    @Autowired
    PersonaRepository personaRepository;

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto persona){
        return personaRepository.save(new Persona(persona)).personaToPersonaOutputDto();
    }
    @Override
    public PersonaOutputDto updatePersona(PersonaInputDto persona){
        Persona person = personaRepository.findById(persona.getId()).orElseThrow();

        if(persona.getNombre()!=null && !persona.getNombre().isBlank())
            person.setNombre(persona.getNombre());
        if(persona.getEdad()!=null && !persona.getEdad().isBlank())
            person.setEdad(persona.getEdad());
        if(persona.getPoblacion()!=null && !persona.getPoblacion().isBlank())
            person.setPoblacion(persona.getPoblacion());

        return personaRepository.save(person).personaToPersonaOutputDto();
    }
    @Override
    public void deletePersonaById(int id){
        personaRepository.findById(id).orElseThrow();
        personaRepository.deleteById(id);
    }
    @Override
    public PersonaOutputDto getPersonaById(int id){
        return personaRepository.findById(id).orElseThrow().personaToPersonaOutputDto();
    }
    @Override
    public List<PersonaOutputDto> getPersonasByName(String nombre){
        return personaRepository.findByNombre(nombre).stream().map(Persona::personaToPersonaOutputDto).toList();
    }
    @Override
    public List<PersonaOutputDto> getAllPersonas(){
        return personaRepository.findAll().stream().map(Persona::personaToPersonaOutputDto).toList();
    }
}
