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
    public PersonaOutputDto addPersona(PersonaInputDto persona) throws Exception{
        validarPersona(persona);
        return personaRepository.save(new Persona(persona)).personaToPersonaOutputDto();
    }
    @Override
    public PersonaOutputDto getPersonaById(int id){
        return personaRepository.findById(id).orElseThrow().personaToPersonaOutputDto();
    }
    @Override
    public List<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        return personaRepository.findAll(pageRequest).getContent().stream().map(Persona::personaToPersonaOutputDto).toList();
    }
    @Override
    public List<PersonaOutputDto> getAllPersonasByUsuarioLike(String usuario){
        return personaRepository.findByUsuarioLike(usuario).stream().map(Persona::personaToPersonaOutputDto).toList();
    }
    @Override
    public PersonaOutputDto updatePersona(PersonaInputDto persona) throws Exception{
        validarPersona(persona);
        personaRepository.findById(persona.getId_persona()).orElseThrow();

        return personaRepository.save(new Persona(persona)).personaToPersonaOutputDto();
    }
    @Override
    public void deletePersonaById(int id){
        personaRepository.findById(id).orElseThrow();
        personaRepository.deleteById(id);
    }

    public PersonaInputDto validarPersona(PersonaInputDto persona) throws Exception {
        String usuario = persona.getUsuario();
        if(usuario==null)
            throw new Exception("El campo usuario es obligatorio");
        if(usuario.length()>10 || usuario.length()<6)
            throw new Exception("El campo usuario tiene que tener entre 6 y 10 caracteres");
        if(persona.getPassword()==null)
            throw new Exception("El campo password es obligatorio");
        if(persona.getName()==null)
            throw new Exception("El campo name es obligatorio");
        if(persona.getCompany_email()==null)
            throw new Exception("El campo company_email es obligatorio");
        if(persona.getPersonal_email()==null)
            throw new Exception("El campo personal_email es obligatorio");
        if(persona.getCity()==null)
            throw new Exception("El campo city es obligatorio");
        if(persona.getCreated_date()==null)
            throw new Exception("El campo created_date es obligatorio");
        return persona;
    }
}
