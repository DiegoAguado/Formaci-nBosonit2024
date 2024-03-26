package com.bosonit.formacion;

import com.bosonit.formacion.domain.Persona;
import com.bosonit.formacion.repository.PersonaRepository;
import com.bosonit.formacion.security.auth.AuthenticationService;
import com.bosonit.formacion.security.auth.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class Block7CrudValidationApplication implements CommandLineRunner {
	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	AuthenticationService authenticationService;

	public static void main(String[] args) {
		SpringApplication.run(Block7CrudValidationApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/getall").allowedOrigins("https://cdpn.io");
				registry.addMapping("/addperson").allowedOrigins("https://cdpn.io");
			}
		};
	}

	@Override
	public void run(String... args){
		Persona persona = new Persona();
		persona.setUsuario("admin");
		persona.setPassword("admin");
		persona.setAdmin(true);
		personaRepository.save(persona);
		authenticationService.register(new RegisterRequest(persona.getUsuario(), persona.getPassword(), persona.isAdmin()));
	}
}
