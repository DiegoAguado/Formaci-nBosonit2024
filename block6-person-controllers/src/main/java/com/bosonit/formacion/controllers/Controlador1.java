package com.bosonit.formacion.controllers;

import com.bosonit.formacion.model.Ciudad;
import com.bosonit.formacion.model.Persona;
import com.bosonit.formacion.service.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {
    @Autowired
    Servicio servicio;
    @GetMapping("/addPersona")
    public Persona addPersona(@RequestHeader Map<String, String> headers){
        return servicio.addPersona(headers);
    }

    @PostMapping("/addCiudad")
    public Ciudad addCiudad(@RequestBody Ciudad ciudad){
        return servicio.addCiudad(ciudad);
    }
}
