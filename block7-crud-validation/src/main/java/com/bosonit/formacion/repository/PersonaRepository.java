package com.bosonit.formacion.repository;

import com.bosonit.formacion.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    List<Persona> findByUsuarioLike(String usuario);
}
