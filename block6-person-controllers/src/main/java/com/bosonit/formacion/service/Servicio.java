package com.bosonit.formacion.service;

import com.bosonit.formacion.model.Ciudad;
import com.bosonit.formacion.model.Persona;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Data
public class Servicio {
    private Persona persona;
    private List<Ciudad> ciudades;
    public Persona addPersona(Map<String, String> headers){
        persona.setNombre(headers.get("nombre"));
        persona.setPoblacion(headers.get("poblacion"));
        persona.setEdad(Integer.parseInt(headers.get("edad")));
        return persona;
    }

    public Ciudad addCiudad(Ciudad ciudad){
        ciudades.add(ciudad);
        return ciudad;
    }
}
