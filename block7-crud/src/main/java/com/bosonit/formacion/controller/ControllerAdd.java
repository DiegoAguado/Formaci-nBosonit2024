package com.bosonit.formacion.controller;

import com.bosonit.formacion.application.PersonaService;
import com.bosonit.formacion.controller.dto.PersonaInputDto;
import com.bosonit.formacion.controller.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")
public class ControllerAdd {
    @Autowired
    PersonaService personaService;

    @PostMapping
    public PersonaOutputDto addPersona(@RequestBody PersonaInputDto persona){
        return personaService.addPersona(persona);
    }
}
