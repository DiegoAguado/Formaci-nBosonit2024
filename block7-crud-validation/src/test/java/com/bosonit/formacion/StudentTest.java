package com.bosonit.formacion;

import com.bosonit.formacion.application.persona.PersonaService;
import com.bosonit.formacion.controller.persona.PersonaController;
import com.bosonit.formacion.controller.persona.dto.PersonaInputDto;
import com.bosonit.formacion.controller.persona.dto.PersonaOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;
import com.bosonit.formacion.customException.UnprocessableEntityException;
import com.bosonit.formacion.repository.PersonaRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentTest {
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    PersonaService personaService;
    @Autowired
    PersonaController personaController;

    @BeforeAll
    void addPersona() throws UnprocessableEntityException {
        PersonaInputDto persona = new PersonaInputDto();
        persona.setUsuario("pepito");
        persona.setPassword("password");
        persona.setName("Pepe");
        persona.setCompany_email("companyemail@email.com");
        persona.setPersonal_email("personalemail@email.com");
        persona.setCity("NYC");
        persona.setCreated_date(new Date());
        persona.setSurname("García");
        persona.setActive(true);
        persona.setUpdatedAt(new Date());
        persona.setImagen_url("url.com");
        persona.setTermination_date(new Date());
        persona.setEsStudent(false);
        personaController.addPersona(persona);
    }

    @Test
    void getPersona() throws EntityNotFoundException {
        ResponseEntity<PersonaOutputDto> persona = personaController.getPersonaById(1, "simple");
        Assertions.assertEquals(200, persona.getStatusCode().value());

        persona = personaController.getPersonaById(1, "full");
        Assertions.assertEquals(200, persona.getStatusCode().value());

        persona = personaController.getPersonaById(1, "");
        Assertions.assertEquals(200, persona.getStatusCode().value());
    }

    @Test
    void getAllPersonas() {
        int size = personaService.getAllPersonas().size();
        Assertions.assertEquals(1, size);

        size = personaService.getAllPersonas(0, 2).size();
        Assertions.assertEquals(1, size);
    }

    @Test
    void getAllPersonasByUsuario() {
        List<PersonaOutputDto> list = personaService.getAllPersonasByUsuarioLike("pepito");
        int size = list.size();
        Assertions.assertEquals(1, size);

        String user = list.get(0).getUsuario();
        Assertions.assertEquals("pepito", user);
    }

    @Test
    void updatePersona() throws UnprocessableEntityException, EntityNotFoundException {
        PersonaOutputDto oldPepito = personaService.getAllPersonasByUsuarioLike("pepito").get(0);

        PersonaInputDto persona = new PersonaInputDto();
        persona.setId_persona(1);
        persona.setUsuario("pepito");
        persona.setPassword("password");
        persona.setName("Pepe");
        persona.setCompany_email("companyemail@email.com");
        persona.setPersonal_email("personalemail@email.com");
        persona.setCity("London");
        persona.setCreated_date(new Date());
        personaService.updatePersona(persona);

        PersonaOutputDto newPepito = personaService.getAllPersonasByUsuarioLike("pepito").get(0);
        Assertions.assertEquals("NYC",oldPepito.getCity());
        Assertions.assertEquals("London",newPepito.getCity());
    }

    @AfterAll
    void deletePersona() throws EntityNotFoundException {
        personaService.deletePersonaById(1);
        Assertions.assertEquals(0,personaService.getAllPersonas().size());
    }
}
