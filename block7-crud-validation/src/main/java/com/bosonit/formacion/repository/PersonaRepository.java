package com.bosonit.formacion.repository;

import com.bosonit.formacion.controller.persona.dto.PersonaOutputDto;
import com.bosonit.formacion.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    List<Persona> findByUsuarioLike(String usuario);
    List<PersonaOutputDto> searchPersonas(HashMap<String, Object> conditions);
}
