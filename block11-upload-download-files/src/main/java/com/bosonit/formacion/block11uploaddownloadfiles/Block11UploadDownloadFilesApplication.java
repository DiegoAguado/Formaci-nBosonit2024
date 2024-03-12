package com.bosonit.formacion.block11uploaddownloadfiles;

import com.bosonit.formacion.block11uploaddownloadfiles.application.FicheroService;
import com.bosonit.formacion.block11uploaddownloadfiles.exception.StorageException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Block11UploadDownloadFilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block11UploadDownloadFilesApplication.class, args);
	}
	@Bean
	CommandLineRunner init(FicheroService ficheroService) throws StorageException {
		return (args -> {
			ficheroService.deleteAll();
			ficheroService.init();
		});
	}

}
