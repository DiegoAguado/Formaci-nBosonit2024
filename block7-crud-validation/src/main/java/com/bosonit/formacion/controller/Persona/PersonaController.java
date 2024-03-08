package com.bosonit.formacion.controller.Persona;

import com.bosonit.formacion.Feign.ProfesorFeign;
import com.bosonit.formacion.application.Persona.PersonaService;
import com.bosonit.formacion.application.Profesor.ProfesorService;
import com.bosonit.formacion.controller.Persona.dto.PersonaInputDto;
import com.bosonit.formacion.controller.Persona.dto.PersonaOutputDto;
import com.bosonit.formacion.controller.Profesor.dto.ProfesorOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;
import com.bosonit.formacion.customException.UnprocessableEntityException;
import com.bosonit.formacion.domain.Profesor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
//@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaService personaService;

    @Autowired
    ProfesorFeign profesorFeign;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto persona) throws UnprocessableEntityException{
        return ResponseEntity.ok().body(personaService.addPersona(persona));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> getPersonaById(@PathVariable int id, @RequestParam(defaultValue = "simple", required = false) String outputType) throws EntityNotFoundException{
        return ResponseEntity.ok().body(personaService.getPersonaById(id, outputType));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PersonaOutputDto>> getAllPersonas(@RequestParam int pageNumber, @RequestParam int pageSize){
        return ResponseEntity.ok().body(personaService.getAllPersonas(pageNumber, pageSize));
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<List<PersonaOutputDto>> getAllPersonasByUsuario(@PathVariable String usuario){
        return ResponseEntity.ok().body(personaService.getAllPersonasByUsuarioLike(usuario));
    }

    @PutMapping
    public ResponseEntity<PersonaOutputDto> updatePersona(@RequestBody PersonaInputDto persona) throws EntityNotFoundException, UnprocessableEntityException{
        return ResponseEntity.ok().body(personaService.updatePersona(persona));
    }

    @DeleteMapping("/{id}")
    public void deletePersonaById(@PathVariable int id) throws EntityNotFoundException{
        personaService.deletePersonaById(id);
    }

    @GetMapping("/profesor/restTemplate/{id}")
    public ProfesorOutputDto getProfesorByIdRestTemplate(@PathVariable String id) throws EntityNotFoundException{
        String url = "http://localhost:8081/profesor/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ProfesorOutputDto> responseEntity = restTemplate.getForEntity(url, ProfesorOutputDto.class);
        return responseEntity.getBody();
    }

    @GetMapping("/profesor/feign/{id}")
    public ProfesorOutputDto getProfesorByIdFeign(@PathVariable String id) throws EntityNotFoundException{
        ResponseEntity<ProfesorOutputDto> profesor = profesorFeign.getProfesorById(id);
        return profesor.getBody();
    }

    //CORS endpoints
    @GetMapping("/getall")
    public ResponseEntity<List<PersonaOutputDto>> getAllPersonasCORS(){
        return ResponseEntity.ok().body(personaService.getAllPersonas());
    }

    @PostMapping("/addperson")
    public ResponseEntity<PersonaOutputDto> addPersonaCORS(@RequestBody PersonaInputDto persona) throws UnprocessableEntityException{
        return ResponseEntity.ok().body(personaService.addPersona(persona));
    }
}
