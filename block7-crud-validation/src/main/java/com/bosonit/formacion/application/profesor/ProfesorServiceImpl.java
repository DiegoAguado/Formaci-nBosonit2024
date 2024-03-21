package com.bosonit.formacion.application.profesor;

import com.bosonit.formacion.controller.profesor.dto.ProfesorInputDto;
import com.bosonit.formacion.controller.profesor.dto.ProfesorOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;
import com.bosonit.formacion.customException.UnprocessableEntityException;
import com.bosonit.formacion.domain.Persona;
import com.bosonit.formacion.domain.Profesor;
import com.bosonit.formacion.repository.PersonaRepository;
import com.bosonit.formacion.repository.ProfesorRepository;
import com.bosonit.formacion.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImpl implements ProfesorService{
    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public ProfesorOutputDto addProfesor(ProfesorInputDto profesorInput) throws EntityNotFoundException, UnprocessableEntityException{
        Profesor profesor = new Profesor(profesorInput);

        Persona persona = personaRepository.findById(profesorInput.getId_persona()).orElseThrow(EntityNotFoundException::new);
        if(persona.isEsStudent()){
            throw new UnprocessableEntityException("La persona con id " + persona.getId_persona() + " no puede ser un profesor");
        }
        profesor.setPersona(persona);
        return profesorRepository.save(profesor).profesorToProfesorOutputDto();
    }
    @Override
    public ProfesorOutputDto getProfesorById(String id) throws EntityNotFoundException {
        return profesorRepository.findById(id).orElseThrow(EntityNotFoundException::new).profesorToProfesorOutputDto();
    }
    @Override
    public List<ProfesorOutputDto> getAllProfesores(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        return profesorRepository.findAll(pageRequest).getContent().stream().map(Profesor::profesorToProfesorOutputDto).toList();
    }
    @Override
    public ProfesorOutputDto updateProfesor(ProfesorInputDto profesor) throws EntityNotFoundException{
        profesorRepository.findById(profesor.getId_profesor()).orElseThrow(EntityNotFoundException::new);

        return profesorRepository.save(new Profesor(profesor)).profesorToProfesorOutputDto();
    }
    @Override
    public void deleteProfesorById(String id) throws EntityNotFoundException{
        profesorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        profesorRepository.deleteById(id);
    }
}
