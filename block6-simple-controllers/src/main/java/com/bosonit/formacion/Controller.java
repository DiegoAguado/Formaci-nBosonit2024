package com.bosonit.formacion;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class Controller {
    @GetMapping("/user/{nombre}")
    public String getNombre(@PathVariable String nombre){
        return "Hola " + nombre;
    }

    @PostMapping("/useradd")
    public Persona postPersona(@RequestBody Persona persona){
        persona.setEdad(persona.getEdad() + 1);
        return persona;
    }
}
