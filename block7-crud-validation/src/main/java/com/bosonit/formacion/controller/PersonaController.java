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

    @PostMapping
    public ResponseEntity<?> addPersona(@RequestBody PersonaInputDto persona){
        try {
            return ResponseEntity.ok().body(personaService.addPersona(persona));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> getPersonaById(@PathVariable int id){
        try {
            return ResponseEntity.ok().body(personaService.getPersonaById(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PersonaOutputDto>> getAllPersonas(@RequestParam int pageNumber, @RequestParam int pageSize){
        return ResponseEntity.ok().body(personaService.getAllPersonas(pageNumber, pageSize));
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<List<PersonaOutputDto>> getPersonaByUsuario(@PathVariable String usuario){
        return ResponseEntity.ok().body(personaService.getAllPersonasByUsuarioLike(usuario));
    }

    @PutMapping
    public ResponseEntity<?> updatePersona(@RequestBody PersonaInputDto persona){
        try{
            return ResponseEntity.ok().body(personaService.updatePersona(persona));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonaById(@PathVariable int id){
        try{
            personaService.deletePersonaById(id);
            return ResponseEntity.ok().body("Persona with id " + id + " was deleted");
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
