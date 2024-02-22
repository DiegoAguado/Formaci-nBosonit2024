package com.bosonit.formacion.application.Persona;

import com.bosonit.formacion.controller.Persona.dto.PersonaInputDto;
import com.bosonit.formacion.controller.Persona.dto.PersonaOutputDto;
import com.bosonit.formacion.controller.Persona.dto.PersonaProfesorOutputDto;
import com.bosonit.formacion.controller.Persona.dto.PersonaStudentOutputDto;
import com.bosonit.formacion.domain.Persona;
import com.bosonit.formacion.customException.EntityNotFoundException;
import com.bosonit.formacion.customException.UnprocessableEntityException;
import com.bosonit.formacion.repository.PersonaRepository;
import com.bosonit.formacion.repository.ProfesorRepository;
import com.bosonit.formacion.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ProfesorRepository profesorRepository;
    @Override
    public PersonaOutputDto addPersona(PersonaInputDto persona) throws UnprocessableEntityException{
        return personaRepository.save(new Persona(validarPersona(persona))).personaToPersonaOutputDto();
    }
    @Override
    public PersonaOutputDto getPersonaById(int id, String outputType) throws EntityNotFoundException{
        Persona persona = personaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(outputType.equalsIgnoreCase("full")){
            if (persona.isEsStudent()) return new PersonaStudentOutputDto(persona, studentRepository.findStudentByPersonaLike(persona.getId_persona()));
            else return new PersonaProfesorOutputDto(persona, profesorRepository.findProfesorByPersonaLike(persona.getId_persona()));
        }
        return persona.personaToPersonaOutputDto();
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
    public PersonaOutputDto updatePersona(PersonaInputDto persona) throws EntityNotFoundException, UnprocessableEntityException {
        personaRepository.findById(validarPersona(persona).getId_persona()).orElseThrow(EntityNotFoundException::new);

        return personaRepository.save(new Persona(persona)).personaToPersonaOutputDto();
    }
    @Override
    public void deletePersonaById(int id) throws EntityNotFoundException{
        personaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        personaRepository.deleteById(id);
    }

    public PersonaInputDto validarPersona(PersonaInputDto persona) throws UnprocessableEntityException {
        String usuario = persona.getUsuario();
        if(usuario==null)
            throw new UnprocessableEntityException("El campo usuario es obligatorio");
        if(usuario.length()>10 || usuario.length()<6)
            throw new UnprocessableEntityException("El campo usuario tiene que tener entre 6 y 10 caracteres");
        if(persona.getPassword()==null)
            throw new UnprocessableEntityException("El campo password es obligatorio");
        if(persona.getName()==null)
            throw new UnprocessableEntityException("El campo name es obligatorio");
        if(persona.getCompany_email()==null)
            throw new UnprocessableEntityException("El campo company_email es obligatorio");
        if(persona.getPersonal_email()==null)
            throw new UnprocessableEntityException("El campo personal_email es obligatorio");
        if(persona.getCity()==null)
            throw new UnprocessableEntityException("El campo city es obligatorio");
        if(persona.getCreated_date()==null)
            throw new UnprocessableEntityException("El campo created_date es obligatorio");
        return persona;
    }
}
