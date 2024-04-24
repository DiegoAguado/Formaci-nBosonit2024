package com.formacion.backweb.domain;

import com.formacion.backweb.controller.dto.ReservaDisponibleOutputDto;
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

    public ReservaDisponibleOutputDto reservaToReservaDisponibleOutputDto(){
        return new ReservaDisponibleOutputDto(
                this.ciudadDestino,
                this.fechaSalida,
                this.horaSalida,
                this.numeroPlazas
        );
    }

    public ReservaDisponible(ReservaDisponibleDto reservaDisponibleDto){
        this.id = reservaDisponibleDto.getId();
        this.ciudadDestino = reservaDisponibleDto.getCiudadDestino();
        this.fechaSalida = reservaDisponibleDto.getFechaSalida();
        this.horaSalida = reservaDisponibleDto.getHoraSalida();
        this.numeroPlazas = reservaDisponibleDto.getNumeroPlazas();
    }
}
