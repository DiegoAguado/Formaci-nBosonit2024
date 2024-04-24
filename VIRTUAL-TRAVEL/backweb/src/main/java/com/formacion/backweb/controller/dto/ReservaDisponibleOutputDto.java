package com.formacion.backweb.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ReservaDisponibleOutputDto {
    String ciudadDestino;
    Date fechaSalida;
    Float horaSalida;
    Integer numeroPlazas;
}
