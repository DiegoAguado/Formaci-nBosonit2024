package com.bosonit.formacion.repository;

import com.bosonit.formacion.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Query(value = "SELECT * FROM Student WHERE id_persona like %?1%", nativeQuery = true)
    Student findStudentByPersonaLike(int id_persona);
}
