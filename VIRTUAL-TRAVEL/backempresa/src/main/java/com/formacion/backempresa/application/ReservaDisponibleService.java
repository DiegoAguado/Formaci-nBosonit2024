package com.formacion.backempresa.application;

import com.formacion.backempresa.controller.dto.ReservaDisponibleInputDto;
import com.formacion.backempresa.controller.dto.ReservaDisponibleOutputDto;
import com.formacion.backempresa.controller.dto.ReservaOutputDto;
import com.formacion.backempresa.domain.ReservaDisponible;
import com.formacion.backempresa.exception.PlazasInsuficientes;
import com.formacion.backempresa.exception.ReservaNoEncontrada;

import java.util.Date;
import java.util.List;

public interface ReservaDisponibleService {
    ReservaDisponibleOutputDto add(ReservaDisponibleInputDto reserva);
    List<ReservaDisponibleOutputDto> getAll();
    void updatePlazas(ReservaOutputDto reserva) throws PlazasInsuficientes, ReservaNoEncontrada;
    ReservaDisponible getReservaDisponible(String ciudadDestino, Date fechaSalida, Float horaSalida) throws ReservaNoEncontrada;
    ReservaDisponibleOutputDto update(ReservaDisponibleInputDto reserva) throws PlazasInsuficientes;
    void delete(Integer id);
}
