package com.formacion.backempresa.controller;

import com.formacion.backempresa.application.ReservaDisponibleService;
import com.formacion.backempresa.controller.dto.ReservaDisponibleInputDto;
import com.formacion.backempresa.controller.dto.ReservaDisponibleOutputDto;
import com.formacion.backempresa.controller.dto.ReservaOutputDto;
import com.formacion.backempresa.domain.ReservaDisponible;
import com.formacion.backempresa.exception.PlazasInsuficientes;
import com.formacion.backempresa.exception.ReservaNoEncontrada;
import com.formacion.backempresa.kafka.KafkaProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v0")
public class ReservaDisponibleController {
    @Autowired
    ReservaDisponibleService reservaDisponibleService;
    @Autowired
    KafkaProducerConfig producer;

    @PostMapping
    public ResponseEntity<ReservaDisponibleOutputDto> add(@RequestBody ReservaDisponibleInputDto reserva) {
        return ResponseEntity.ok().body(reservaDisponibleService.add(reserva));
    }

    @GetMapping
    public ResponseEntity<List<ReservaDisponibleOutputDto>> getAll() {
        return ResponseEntity.ok().body(reservaDisponibleService.getAll());
    }

    @PostMapping("/reserva")
    public ResponseEntity<ReservaOutputDto> updatePlazas(@RequestBody ReservaOutputDto reserva) throws PlazasInsuficientes, ReservaNoEncontrada {
        try{
            reservaDisponibleService.updatePlazas(reserva);//no funca
            producer.sendSincronizacion();
            return ResponseEntity.ok().body(reserva);
        }catch (PlazasInsuficientes e){
            producer.sendSincronizacion();
            throw new PlazasInsuficientes();
        }catch (ReservaNoEncontrada e){
            producer.sendSincronizacion();
            throw new ReservaNoEncontrada();
        }
    }

    @PutMapping
    public ResponseEntity<ReservaDisponibleOutputDto> update(@RequestBody ReservaDisponibleInputDto reserva){
        reservaDisponibleService.update(reserva);
        producer.sendSincronizacion();
        return ResponseEntity.ok().body(new ReservaDisponible(reserva).reservaToReservaDisponibleOutputDto());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        reservaDisponibleService.delete(id);
    }
}
