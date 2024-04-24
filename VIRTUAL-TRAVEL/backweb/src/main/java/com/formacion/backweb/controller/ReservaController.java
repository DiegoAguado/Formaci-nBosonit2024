package com.formacion.backweb.controller;

import com.formacion.backweb.application.ReservaDisponibleService;
import com.formacion.backweb.application.ReservaService;
import com.formacion.backweb.controller.dto.ReservaDisponibleOutputDto;
import com.formacion.backweb.controller.dto.ReservaInputDto;
import com.formacion.backweb.controller.dto.ReservaOutputDto;
import com.formacion.backweb.exception.PlazasInsuficientes;
import com.formacion.backweb.exception.ReservaNoEncontrada;
import com.formacion.backweb.kafka.KafkaProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v0")
public class ReservaController {
    @Autowired
    ReservaService reservaService;
    @Autowired
    ReservaDisponibleService reservaDisponibleService;

    @Autowired
    KafkaProducerConfig producer;

    @PostMapping("/reserva")
    public ResponseEntity<ReservaOutputDto> add(@RequestBody ReservaInputDto reserva) throws ReservaNoEncontrada, PlazasInsuficientes {
        return ResponseEntity.ok().body(producer.sendReserva(reservaDisponibleService.updatePlazas(reservaService.add(reserva))));
    }

    @GetMapping("/reserva/{ciudadDestino}")
    public ResponseEntity<List<ReservaOutputDto>> getByCiudadDestino(
            @PathVariable String ciudadDestino,
            @RequestParam(required = true) @DateTimeFormat(pattern = "dd-MM-yyyy") Date fechaInferior,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date fechaSuperior,
            @RequestParam(required = false) Float horaInferior,
            @RequestParam(required = false) Float horaSuperior){
        HashMap<String, Object> condiciones = new HashMap<>();
        condiciones.put("ciudadDestino", ciudadDestino);
        condiciones.put("fechaInferior", fechaInferior);

        if(fechaSuperior != null)
            condiciones.put("fechaSuperior", fechaSuperior);

        if(horaInferior != null)
            condiciones.put("horaInferior", horaInferior);

        if(horaSuperior != null)
            condiciones.put("horaSuperior", horaSuperior);

        return ResponseEntity.ok()
                .body(reservaService.getByCiudadDestino(condiciones));
    }

    @GetMapping("/disponible/{ciudadDestino}")
    public ResponseEntity<List<ReservaDisponibleOutputDto>> getPlazasDisponibles(
            @PathVariable String ciudadDestino,
            @RequestParam(required = true) @DateTimeFormat(pattern = "dd-MM-yyyy") Date fechaInferior,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date fechaSuperior,
            @RequestParam(required = false) Float horaInferior,
            @RequestParam(required = false) Float horaSuperior){
        HashMap<String, Object> condiciones = new HashMap<>();
        condiciones.put("ciudadDestino", ciudadDestino);
        condiciones.put("fechaInferior", fechaInferior);

        if(fechaSuperior != null)
            condiciones.put("fechaSuperior", fechaSuperior);

        if(horaInferior != null)
            condiciones.put("horaInferior", horaInferior);

        if(horaSuperior != null)
            condiciones.put("horaSuperior", horaSuperior);

        return ResponseEntity.ok()
                .body(reservaDisponibleService.getPlazasDisponibles(condiciones));
    }
}
