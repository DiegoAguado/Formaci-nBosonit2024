package com.bosonit.formacion.application.Student;

import com.bosonit.formacion.controller.Student.dto.StudentFullOutputDto;
import com.bosonit.formacion.controller.Student.dto.StudentInputDto;
import com.bosonit.formacion.controller.Student.dto.StudentOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;
import com.bosonit.formacion.customException.UnprocessableEntityException;

import java.util.List;

public interface StudentService {
    StudentOutputDto addStudent(StudentInputDto student) throws EntityNotFoundException, UnprocessableEntityException;
    StudentOutputDto addAsignaturasToStudent(String id_student, List<String> id_asignatura)throws EntityNotFoundException;
    StudentOutputDto getStudentById(String id) throws EntityNotFoundException;
    StudentFullOutputDto getStudentByIdFull(String id) throws EntityNotFoundException;
    List<StudentOutputDto> getAllStudents(int pageNumber, int pageSize);
    StudentOutputDto updateStudent(StudentInputDto student) throws EntityNotFoundException;
    StudentOutputDto deleteStudentById(String id) throws EntityNotFoundException;
    StudentOutputDto deleteAsignaturasToStudent(String id, List<String> id_asignatura) throws EntityNotFoundException;
}
