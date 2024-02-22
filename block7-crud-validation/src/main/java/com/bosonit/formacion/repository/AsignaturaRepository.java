package com.bosonit.formacion.repository;

import com.bosonit.formacion.domain.Asignatura;
import com.bosonit.formacion.domain.Profesor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AsignaturaRepository extends JpaRepository<Asignatura, String> {
    @Query(value = "SELECT * FROM Asignatura LEFT JOIN Student_Asignatura ON Asignatura.id = Student_Asignatura.asignatura_id WHERE Student_Asignatura.student_id like ?1", nativeQuery = true)
    Page<Asignatura> findAllAsignaturasEstudianteLike(String student_id, PageRequest pageRequest);
}
