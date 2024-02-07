package com.bosonit.formacion.controllers;

import com.bosonit.formacion.model.Ciudad;
import com.bosonit.formacion.model.Persona;
import com.bosonit.formacion.service.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {
    @Autowired
    Servicio servicio;
    @GetMapping("/getPersona")
    public Persona getPersona(){
        int edad = servicio.getPersona().getEdad();
        servicio.getPersona().setEdad(edad * 2);
        return servicio.getPersona();
    }

    @GetMapping("/getCiudades")
    public List<Ciudad> addCiudad(){
        return servicio.getCiudades();
    }
}
