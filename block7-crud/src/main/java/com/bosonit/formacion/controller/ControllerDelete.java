package com.bosonit.formacion.controller;

import com.bosonit.formacion.application.PersonaService;
import com.bosonit.formacion.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")
public class ControllerDelete {
    @Autowired
    PersonaService personaService;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersonaById(@PathVariable int id){
        try{
            PersonaOutputDto persona = personaService.getPersonaById(id);
            personaService.deletePersonaById(id);
            return ResponseEntity.ok().body(persona);
        }catch (Exception e){
            return ResponseEntity.status(404).body("Persona no encontrada");
        }
    }
}
