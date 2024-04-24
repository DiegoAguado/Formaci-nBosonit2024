package com.formacion.backempresa.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Date;

@Getter
@AllArgsConstructor
public class ReservaDisponibleInputDto {
    Integer id;
    String ciudadDestino;
    Date fechaSalida;
    Float horaSalida;
    Integer numeroPlazas;
}
