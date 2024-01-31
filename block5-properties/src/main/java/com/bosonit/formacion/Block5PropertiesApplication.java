package com.bosonit.formacion;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class Block5PropertiesApplication implements CommandLineRunner {
	@Value("${greeting}")
	private String greeting;
	@Value("${my.number}")
	private int number;
	@Value("${new.property:new.property no tiene valor}")
	private String newProperty;
	@Value("${MYURL:MYURL no tiene valor}")
	private String myurl;
	@Value("${MYURL2:NO_tengo_valor}")
	private String myurl2;

	public static void main(String[] args) {
		SpringApplication.run(Block5PropertiesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(myurl);
		System.out.println(myurl2);
	}

	@PostConstruct
	public void properties(){
		System.out.println("El valor de greeting es: " + greeting);
		System.out.println("El valor de my.number es: " + number);
		System.out.println("El valor de new.property es: " + newProperty);
	}
}
