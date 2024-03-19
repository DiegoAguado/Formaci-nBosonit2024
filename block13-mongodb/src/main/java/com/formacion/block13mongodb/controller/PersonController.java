package com.formacion.block13mongodb.controller;

import com.formacion.block13mongodb.application.PersonService;
import com.formacion.block13mongodb.controller.dto.PersonInputDto;
import com.formacion.block13mongodb.controller.dto.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto person) {
        return ResponseEntity.ok().body(personService.addPerson(person));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonOutputDto> getPersonById(@PathVariable int id) {
        return ResponseEntity.ok().body(personService.getPersonById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PersonOutputDto>> getAll(@RequestParam int pageNumber, @RequestParam int pageSize){
        return ResponseEntity.ok().body(personService.getAll(pageNumber, pageSize));
    }

    @PutMapping
    public ResponseEntity<PersonOutputDto> updatePerson(@RequestBody PersonInputDto person) {
        return ResponseEntity.ok().body(personService.updatePerson(person));
    }

    @DeleteMapping("/{id}")
    public void deletePersonById(@PathVariable int id) {
        personService.deletePersonById(id);
    }
}
