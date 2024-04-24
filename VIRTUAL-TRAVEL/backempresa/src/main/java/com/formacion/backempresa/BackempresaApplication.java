package com.formacion.backempresa;

import com.formacion.backempresa.controller.dto.ReservaDisponibleInputDto;
import com.formacion.backempresa.domain.ReservaDisponible;
import com.formacion.backempresa.kafka.KafkaProducerConfig;
import com.formacion.backempresa.repository.ReservaDisponibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;
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
		ReservaDisponibleInputDto reservaDisponible1 = new ReservaDisponibleInputDto(1, "Barcelona", new Date(2024-1900, Calendar.APRIL,16), Float.parseFloat("8"), 40);
		reservaDisponibleRepository.save(new ReservaDisponible(reservaDisponible1));

		ReservaDisponibleInputDto reservaDisponible2 = new ReservaDisponibleInputDto(2, "Barcelona", new Date(2024-1900,Calendar.APRIL,16), Float.parseFloat("12"), 40);
		reservaDisponibleRepository.save(new ReservaDisponible(reservaDisponible2));

		ReservaDisponibleInputDto reservaDisponible3 = new ReservaDisponibleInputDto(3, "Barcelona", new Date(2024-1900, Calendar.APRIL, 16), Float.parseFloat("16"), 40);
		reservaDisponibleRepository.save(new ReservaDisponible(reservaDisponible3));

		ReservaDisponibleInputDto reservaDisponible4 = new ReservaDisponibleInputDto(4, "Barcelona", new Date(2024-1900,Calendar.APRIL,16), Float.parseFloat("20"), 40);
		reservaDisponibleRepository.save(new ReservaDisponible(reservaDisponible4));

		kafkaProducerConfig.sendSincronizacion();
	}
}
