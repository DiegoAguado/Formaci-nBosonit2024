package com.bosonit.formacion.controller;

import com.bosonit.formacion.application.PersonaService;
import com.bosonit.formacion.controller.dto.PersonaInputDto;
import com.bosonit.formacion.controller.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaService personaService;

    private static final String exceptionMessage = "Persona no encontrada";

    @PostMapping
    public PersonaOutputDto addPersona(@RequestBody PersonaInputDto persona){
        return personaService.addPersona(persona);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonaById(@PathVariable int id){
        try {
            return ResponseEntity.ok().body(personaService.getPersonaById(id));
        }catch (Exception e){
            return ResponseEntity.status(404).body(exceptionMessage);
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

    @PutMapping
    public ResponseEntity<?> updatePersonaById(@RequestBody PersonaInputDto persona){
        try {
            personaService.getPersonaById(persona.getId());
            return ResponseEntity.ok().body(personaService.updatePersona(persona));
        }catch (Exception e){
            return ResponseEntity.status(404).body(exceptionMessage);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersonaById(@PathVariable int id){
        try{
            PersonaOutputDto persona = personaService.getPersonaById(id);
            personaService.deletePersonaById(id);
            return ResponseEntity.ok().body(persona);
        }catch (Exception e){
            return ResponseEntity.status(404).body(exceptionMessage);
        }
    }
}
