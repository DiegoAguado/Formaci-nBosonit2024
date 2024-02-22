package com.bosonit.formacion.repository;

import com.bosonit.formacion.domain.Persona;
import com.bosonit.formacion.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfesorRepository extends JpaRepository<Profesor, String> {
    @Query(value = "SELECT * FROM Profesor WHERE id_persona like %?1%", nativeQuery = true)
    Profesor findProfesorByPersonaLike(int id_persona);
}
