package com.bosonit.formacion.application.Student;

import com.bosonit.formacion.controller.Student.dto.StudentFullOutputDto;
import com.bosonit.formacion.controller.Student.dto.StudentInputDto;
import com.bosonit.formacion.controller.Student.dto.StudentOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;
import com.bosonit.formacion.customException.UnprocessableEntityException;
import com.bosonit.formacion.domain.Asignatura;
import com.bosonit.formacion.domain.Persona;
import com.bosonit.formacion.domain.Profesor;
import com.bosonit.formacion.domain.Student;
import com.bosonit.formacion.repository.AsignaturaRepository;
import com.bosonit.formacion.repository.PersonaRepository;
import com.bosonit.formacion.repository.ProfesorRepository;
import com.bosonit.formacion.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    AsignaturaRepository asignaturaRepository;
    @Override
    public StudentOutputDto addStudent(StudentInputDto studentInput) throws EntityNotFoundException, UnprocessableEntityException{
        Student student = new Student(studentInput);

        Persona persona = personaRepository.findById(studentInput.getId_persona()).orElseThrow(EntityNotFoundException::new);
        if(!persona.isEsStudent())
            throw new UnprocessableEntityException("La persona con id " + persona.getId_persona() + " no puede ser un profesor");

        student.setPersona(persona);

        return studentRepository.save(student).studentToStudentOutputDto();
    }

    @Override
    public StudentOutputDto addAsignaturasToStudent(String id_student, List<String> id_asignatura)throws EntityNotFoundException{
        Student student = studentRepository.findById(id_student).orElseThrow(EntityNotFoundException::new);

        for(String id : id_asignatura) {
            boolean existe = false;
            for(Asignatura asignatura : student.getAsignatura())
                if(id.equals(asignatura.getId_asignatura())) existe = true;

            if(!existe) student.getAsignatura().add(asignaturaRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        }

        return studentRepository.save(student).studentToStudentOutputDto();
    }
    @Override
    public StudentOutputDto getStudentById(String id) throws EntityNotFoundException {
        return studentRepository.findById(id).orElseThrow(EntityNotFoundException::new).studentToStudentOutputDto();
    }
    @Override
    public StudentFullOutputDto getStudentByIdFull(String id) throws EntityNotFoundException {
        return studentRepository.findById(id).orElseThrow(EntityNotFoundException::new).studentToStudentFullOutputDto();
    }
    @Override
    public List<StudentOutputDto> getAllStudents(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        return studentRepository.findAll(pageRequest).getContent().stream().map(Student::studentToStudentOutputDto).toList();
    }
    @Override
    public StudentOutputDto updateStudent(StudentInputDto student) throws EntityNotFoundException{
        studentRepository.findById(student.getId_student()).orElseThrow(EntityNotFoundException::new);

        return studentRepository.save(new Student(student)).studentToStudentOutputDto();
    }
    @Override
    public StudentOutputDto deleteStudentById(String id) throws EntityNotFoundException{
        Student student = studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        studentRepository.deleteById(id);
        return student.studentToStudentOutputDto();
    }

    @Override
    public StudentOutputDto deleteAsignaturasToStudent(String id_student, List<String> id_asignatura) throws EntityNotFoundException{
        Student student = studentRepository.findById(id_student).orElseThrow(EntityNotFoundException::new);

        for(String id : id_asignatura) {
            Asignatura asignatura = asignaturaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            student.getAsignatura().remove(asignatura);
            asignatura.getStudent().remove(student);
            asignaturaRepository.save(asignatura);
        }
        return student.studentToStudentOutputDto();
    }
}
