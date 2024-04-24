package com.formacion.backempresa;

import com.formacion.backempresa.controller.dto.ReservaDisponibleInputDto;
import com.formacion.backempresa.domain.ReservaDisponible;
import com.formacion.backempresa.kafka.KafkaProducerConfig;
import com.formacion.backempresa.repository.ReservaDisponibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Date;

@SpringBootApplication
public class BackempresaApplication implements CommandLineRunner {
	@Autowired
	ReservaDisponibleRepository reservaDisponibleRepository;
	@Autowired
	KafkaProducerConfig kafkaProducerConfig;

	public static void main(String[] args) {
		SpringApplication.run(BackempresaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String ciudad = "Valencia";
		for(int i=1; i<=16;i++){
			if(i>4 && i<=8) ciudad = "Madrid";
			if(i>8 && i<=12) ciudad = "Barcelona";
			if(i>12) ciudad = "Bilbao";
			ReservaDisponibleInputDto reserva = new ReservaDisponibleInputDto(i, ciudad, new Date(2024-1900,4-1,24), Float.parseFloat("8"), 40);
			reservaDisponibleRepository.save(new ReservaDisponible(reserva));
		}
		kafkaProducerConfig.sendSincronizacion();
	}
}
