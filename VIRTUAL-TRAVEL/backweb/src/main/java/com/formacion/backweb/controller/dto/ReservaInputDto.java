package com.formacion.backweb.controller.dto;

import lombok.Getter;
import java.util.Date;

@Getter
public class ReservaInputDto {
    Integer id;
    String ciudadDestino;
    String nombre;
    String apellido;
    String telefono;
    String email;
    Date fechaReserva;
    Float horaReserva;
}
