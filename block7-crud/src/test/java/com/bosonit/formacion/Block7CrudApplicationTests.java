package com.bosonit.formacion;

import com.bosonit.formacion.application.PersonaService;
import com.bosonit.formacion.controller.PersonaController;
import com.bosonit.formacion.controller.dto.PersonaInputDto;
import com.bosonit.formacion.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.domain.Persona;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)class Block7CrudApplicationTests {
	@Autowired
	PersonaController personaController;

	@BeforeAll
	void addPersona() {
		PersonaInputDto persona = new PersonaInputDto(1, "Pedro", "36", "Logroño");
		personaController.addPersona(persona);
	}

	@Test
	void getPersonaById200() {
		ResponseEntity<?> persona = personaController.getPersonaById(1);
		Assertions.assertEquals(200, persona.getStatusCode().value());
	}

	@Test
	void getPersonaById404() {
		ResponseEntity<?> persona = personaController.getPersonaById(2);
		Assertions.assertEquals(404, persona.getStatusCode().value());
	}

	@Test
	void getPersonaByNombre() {
		List<PersonaOutputDto> persona = personaController.getPersonasByNombre("Pedro");
		Assertions.assertEquals(1, persona.size());
	}

	@Test
	void getAll() {
		personaController.addPersona(new PersonaInputDto(
				2,
				"Juan",
				"29",
				"Bilbao"
		));
		List<PersonaOutputDto> persona = personaController.getAll();
		Assertions.assertEquals(2, persona.size());
	}

	@Test
	void updatePersonaById200() {
		ResponseEntity<?> respuesta = personaController.updatePersonaById(new PersonaInputDto(
				1,
				"Pedro",
				"37",
				"Madrid"
		));
		Assertions.assertEquals(200, respuesta.getStatusCode().value());
	}

	@Test
	void updatePersonaById200Empty(){
		PersonaInputDto persona = new PersonaInputDto(1, "", "", "");
		ResponseEntity<?> respuesta = personaController.updatePersonaById(persona);
		Assertions.assertEquals(200, respuesta.getStatusCode().value());
	}

	@Test
	void updatePersonaById404() {
		ResponseEntity<?> respuesta = personaController.updatePersonaById(new PersonaInputDto(
				3,
				"Pedro",
				"37",
				"Madrid"
		));
		Assertions.assertEquals(404, respuesta.getStatusCode().value());
	}

	@AfterAll
	void deletePersonaById200() {
		ResponseEntity<?> respuesta = personaController.deletePersonaById(1);
		Assertions.assertEquals(200, respuesta.getStatusCode().value());
	}

	@AfterAll
	void deletePersonaById404() {
		ResponseEntity<?> respuesta = personaController.deletePersonaById(3);
		Assertions.assertEquals(404, respuesta.getStatusCode().value());
	}
}
