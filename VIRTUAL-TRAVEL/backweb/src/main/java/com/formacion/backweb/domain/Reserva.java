package com.formacion.backweb.domain;

import com.formacion.backweb.controller.dto.ReservaInputDto;
import com.formacion.backweb.controller.dto.ReservaOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue
    Integer id;
    String ciudadDestino;
    String nombre;
    String apellido;
    String telefono;
    String email;
    Date fechaReserva;
    Float horaReserva;

    public Reserva(ReservaInputDto reserva){
        this.id = reserva.getId();
        this.ciudadDestino = reserva.getCiudadDestino();
        this.nombre = reserva.getNombre();
        this.apellido = reserva.getApellido();
        this.telefono = reserva.getTelefono();
        this.email = reserva.getEmail();
        this.fechaReserva = reserva.getFechaReserva();
        this.horaReserva = reserva.getHoraReserva();
    }

    public ReservaOutputDto reservaToReservaOutputDto(){
        return new ReservaOutputDto(
                this.id,
                this.ciudadDestino,
                this.nombre,
                this.apellido,
                this.telefono,
                this.email,
                this.fechaReserva,
                this.horaReserva
        );
    }
}
