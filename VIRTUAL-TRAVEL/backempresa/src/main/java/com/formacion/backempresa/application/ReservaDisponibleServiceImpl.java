package com.formacion.backempresa.application;

import com.formacion.backempresa.controller.dto.ReservaDisponibleInputDto;
import com.formacion.backempresa.controller.dto.ReservaDisponibleOutputDto;
import com.formacion.backempresa.controller.dto.ReservaOutputDto;
import com.formacion.backempresa.domain.ReservaDisponible;
import com.formacion.backempresa.exception.PlazasInsuficientes;
import com.formacion.backempresa.exception.ReservaNoEncontrada;
import com.formacion.backempresa.repository.ReservaDisponibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservaDisponibleServiceImpl implements ReservaDisponibleService{
    @Autowired
    ReservaDisponibleRepository reservaDisponibleRepository;

    @Override
    public ReservaDisponibleOutputDto add(ReservaDisponibleInputDto reserva) {
        return reservaDisponibleRepository.save(new ReservaDisponible(reserva)).reservaToReservaDisponibleOutputDto();
    }

    @Override
    public List<ReservaDisponibleOutputDto> getAll() {
        return reservaDisponibleRepository.findAll().stream().map(ReservaDisponible::reservaToReservaDisponibleOutputDto).toList();
    }

    @Override
    public void updatePlazas(ReservaOutputDto reserva) throws PlazasInsuficientes, ReservaNoEncontrada {
        String ciudadDestino = reserva.getCiudadDestino();
        Date fechaSalida = reserva.getFechaReserva();
        Float horaSalida = reserva.getHoraReserva();
        ReservaDisponible reservaDisponible = getReservaDisponible(ciudadDestino, fechaSalida, horaSalida);

        int numPlazas = reservaDisponible.getNumeroPlazas();
        if(numPlazas>0){
            reservaDisponible.setNumeroPlazas(numPlazas-1);
            reservaDisponibleRepository.save(reservaDisponible);
        }
        else{
            throw new PlazasInsuficientes();
        }
    }

    @Override
    public ReservaDisponible getReservaDisponible(String ciudadDestino, Date fechaSalida, Float horaSalida) throws ReservaNoEncontrada {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(fechaSalida);
        return reservaDisponibleRepository.findReservaDisponible(
                ciudadDestino,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH)+1,
                calendar.get(Calendar.DAY_OF_MONTH),
                horaSalida)
                .orElseThrow(ReservaNoEncontrada::new);
    }

    @Override
    public ReservaDisponibleOutputDto update(ReservaDisponibleInputDto reserva)throws PlazasInsuficientes{
        reservaDisponibleRepository.findById(reserva.getId()).orElseThrow(ReservaNoEncontrada::new);
        if(reserva.getNumeroPlazas()<0)
            throw new PlazasInsuficientes("Número de plazas inválido");
        return reservaDisponibleRepository.save(new ReservaDisponible(reserva)).reservaToReservaDisponibleOutputDto();
    }

    @Override
    public void delete(Integer id){
        reservaDisponibleRepository.deleteById(id);
    }
}
