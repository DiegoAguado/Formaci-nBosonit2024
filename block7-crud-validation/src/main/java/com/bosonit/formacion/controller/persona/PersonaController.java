package com.bosonit.formacion.controller.persona;

import com.bosonit.formacion.feign.ProfesorFeign;
import com.bosonit.formacion.application.persona.PersonaService;
import com.bosonit.formacion.controller.persona.dto.PersonaInputDto;
import com.bosonit.formacion.controller.persona.dto.PersonaOutputDto;
import com.bosonit.formacion.controller.profesor.dto.ProfesorOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;
import com.bosonit.formacion.customException.UnprocessableEntityException;
import com.bosonit.formacion.repository.PersonaRepository;
import com.bosonit.formacion.security.auth.AuthenticationRequest;
import com.bosonit.formacion.security.auth.AuthenticationResponse;
import com.bosonit.formacion.security.auth.AuthenticationService;
import com.bosonit.formacion.security.auth.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaService personaService;
    @Autowired
    ProfesorFeign profesorFeign;
    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    AuthenticationService service;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto persona) throws UnprocessableEntityException{
        service.register(new RegisterRequest(persona.getUsuario(), persona.getPassword(), persona.isAdmin()));
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

    @GetMapping("/name/{name}")
    public ResponseEntity<List<PersonaOutputDto>> getAllPersonasByName(@PathVariable String name){
        return ResponseEntity.ok().body(personaService.getAllPersonasByName(name));
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

    //endpoint con usuario name surname created_date superior o inferior ordenar por user o name
    @GetMapping("/buscar")
    public Iterable<PersonaOutputDto> searchPersonas(
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) @DateTimeFormat(pattern="yyyy/MM/dd") Date created_date,
            @RequestParam(required = false) String datecondition,
            @RequestParam(required = false) String orderBy,
            @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam Integer pageNumber
    ){
        HashMap<String, Object> conditions = new HashMap<>();
        if (usuario!=null)
            conditions.put("usuario", usuario);
        if (name!=null)
            conditions.put("name", name);
        if (surname!=null)
            conditions.put("surname", surname);
        if (created_date!=null)
            conditions.put("created_date", created_date);
        if (datecondition!=null)
            conditions.put("datecondition", datecondition);
        if (orderBy!=null)
            conditions.put("orderBy", orderBy);

        conditions.put("pageSize", pageSize);
        conditions.put("pageNumber", pageNumber);

        return personaRepository.searchPersonas(conditions);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }
}
