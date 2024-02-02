package com.bosonit.formacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Block5ProfilesApplication implements CommandLineRunner{

	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(Block5ProfilesApplication.class, args);
	}
	@Bean
	@Profile("INT")
	PerfilInterface getINT()
	{
		return new PerfilINT();
	}

	@Bean
	@Profile("local")
	PerfilInterface getLocal()
	{
		return new PerfilLocal();
	}

	@Bean
	@Profile("PRO")
	PerfilInterface getPRO()
	{
		return new PerfilPRO();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(environment.getActiveProfiles()[0]);
	}
}
