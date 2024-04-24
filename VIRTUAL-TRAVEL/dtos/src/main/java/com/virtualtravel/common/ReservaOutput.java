package com.virtualtravel.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaOutput {
    Integer id;
    String ciudadDestino;
    String nombre;
    String apellido;
    String telefono;
    String email;
    Date fechaReserva;
    Float horaReserva;
}
