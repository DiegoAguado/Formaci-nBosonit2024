package com.virtualtravel.common;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDisponibleDto {
    Integer id;
    String ciudadDestino;
    Date fechaSalida;
    Float horaSalida;
    Integer numeroPlazas;
}
