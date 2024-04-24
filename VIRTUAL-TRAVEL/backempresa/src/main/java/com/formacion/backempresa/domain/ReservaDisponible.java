package com.formacion.backempresa.domain;

import com.formacion.backempresa.controller.dto.ReservaDisponibleInputDto;
import com.formacion.backempresa.controller.dto.ReservaDisponibleOutputDto;
import com.virtualtravel.common.ReservaDisponibleDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDisponible {
    @Id
    @GeneratedValue
    Integer id;
    String ciudadDestino;
    Date fechaSalida;
    Float horaSalida;
    Integer numeroPlazas;

    public ReservaDisponible(ReservaDisponibleInputDto reserva){
        this.id = reserva.getId();
        this.ciudadDestino = reserva.getCiudadDestino();
        this.fechaSalida = reserva.getFechaSalida();
        this.horaSalida = reserva.getHoraSalida();
        this.numeroPlazas = reserva.getNumeroPlazas();
    }

    public ReservaDisponibleOutputDto reservaToReservaDisponibleOutputDto(){
        return new ReservaDisponibleOutputDto(
                this.ciudadDestino,
                this.fechaSalida,
                this.horaSalida,
                this.numeroPlazas
        );
    }

    public ReservaDisponibleDto transform(){
        return new ReservaDisponibleDto(
                this.id,
                this.ciudadDestino,
                this.fechaSalida,
                this.horaSalida,
                this.numeroPlazas
        );
    }
}
