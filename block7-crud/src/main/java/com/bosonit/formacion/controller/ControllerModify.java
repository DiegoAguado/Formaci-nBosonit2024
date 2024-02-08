package com.bosonit.formacion.controller;

import com.bosonit.formacion.application.PersonaService;
import com.bosonit.formacion.controller.dto.PersonaInputDto;
import com.bosonit.formacion.controller.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class ControllerModify {
    @Autowired
    PersonaService personaService;

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePersonaById(@RequestBody PersonaInputDto persona){
        try {
            personaService.getPersonaById(persona.getId());
            return ResponseEntity.ok().body(personaService.updatePersona(persona));
        }catch (Exception e){
            return ResponseEntity.status(404).body("Persona no encontrada");
        }
    }
}
