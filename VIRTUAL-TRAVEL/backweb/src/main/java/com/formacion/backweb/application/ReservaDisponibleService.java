package com.formacion.backweb.application;

import com.formacion.backweb.controller.dto.ReservaDisponibleOutputDto;
import com.formacion.backweb.controller.dto.ReservaOutputDto;
import com.formacion.backweb.domain.ReservaDisponible;
import com.formacion.backweb.exception.PlazasInsuficientes;
import com.formacion.backweb.exception.ReservaNoEncontrada;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface ReservaDisponibleService {
    ReservaOutputDto updatePlazas(ReservaOutputDto reserva) throws PlazasInsuficientes, ReservaNoEncontrada;
    ReservaDisponible getReservaDisponible(String ciudadDestino, Date fechaSalida, Float horaSalida) throws ReservaNoEncontrada;
    List<ReservaDisponibleOutputDto> getPlazasDisponibles(HashMap<String, Object> condiciones);
}
