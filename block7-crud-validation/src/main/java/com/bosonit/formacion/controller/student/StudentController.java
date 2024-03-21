package com.bosonit.formacion.controller.student;

import com.bosonit.formacion.application.student.StudentService;
import com.bosonit.formacion.controller.student.dto.StudentInputDto;
import com.bosonit.formacion.controller.student.dto.StudentOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;
import com.bosonit.formacion.customException.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentOutputDto> addStudent(@RequestBody StudentInputDto student) throws EntityNotFoundException, UnprocessableEntityException {
        return ResponseEntity.ok().body(studentService.addStudent(student));
    }

    @PostMapping("/{id}")
    public ResponseEntity<StudentOutputDto> addAsignaturasToStudent(@PathVariable String id, @RequestBody List<String> id_asignatura) throws EntityNotFoundException {
        return ResponseEntity.ok().body(studentService.addAsignaturasToStudent(id, id_asignatura));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable String id, @RequestParam(defaultValue = "simple", required = false) String outputType) throws EntityNotFoundException {
        if(outputType.equalsIgnoreCase("simple"))
            return ResponseEntity.ok().body(studentService.getStudentById(id));
        if(outputType.equalsIgnoreCase("full"))
            return ResponseEntity.ok().body(studentService.getStudentByIdFull(id));
        else
            return ResponseEntity.badRequest().body("Opción no válida");
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentOutputDto>> getAllStudents(@RequestParam int pageNumber, @RequestParam int pageSize){
        return ResponseEntity.ok().body(studentService.getAllStudents(pageNumber, pageSize));
    }

    @PutMapping
    public ResponseEntity<StudentOutputDto> updateStudent(@RequestBody StudentInputDto student) throws EntityNotFoundException{
        return ResponseEntity.ok().body(studentService.updateStudent(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentOutputDto> deleteStudentById(@PathVariable String id) throws EntityNotFoundException{
        return ResponseEntity.ok().body(studentService.deleteStudentById(id));
    }

    @DeleteMapping("/asignaturas/{id}")
    public void deleteAsignaturasToStudent(@PathVariable String id, @RequestBody List<String> id_asignatura) throws EntityNotFoundException {
        studentService.deleteAsignaturasToStudent(id, id_asignatura);
    }
}
