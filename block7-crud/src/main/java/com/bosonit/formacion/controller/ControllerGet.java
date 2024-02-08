package com.bosonit.formacion.controller;

import com.bosonit.formacion.application.PersonaService;
import com.bosonit.formacion.controller.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/persona")
public class ControllerGet {
    @Autowired
    PersonaService personaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonaById(@PathVariable int id){
        try {
            return ResponseEntity.ok().body(personaService.getPersonaById(id));
        }catch (Exception e){
            return ResponseEntity.status(404).body("Persona no encontrada");
        }
    }

    @GetMapping("/nombre/{nombre}")
    public List<PersonaOutputDto> getPersonasByNombre(@PathVariable String nombre){
        return personaService.getPersonasByName(nombre);
    }

    @GetMapping
    public List<PersonaOutputDto> getAll(){
        return personaService.getAllPersonas();
    }
}
