package com.formacion.backweb.application;

import com.formacion.backweb.controller.dto.ReservaDisponibleOutputDto;
import com.formacion.backweb.controller.dto.ReservaOutputDto;
import com.formacion.backweb.domain.ReservaDisponible;
import com.formacion.backweb.exception.PlazasInsuficientes;
import com.formacion.backweb.exception.ReservaNoEncontrada;
import com.formacion.backweb.repository.ReservaDisponibleRepository;
import com.formacion.backweb.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservaDisponibleServiceImpl implements ReservaDisponibleService{
    @Autowired
    ReservaDisponibleRepository reservaDisponibleRepository;
    @Autowired
    ReservaRepository reservaRepository;

    @Override
    public ReservaOutputDto updatePlazas(ReservaOutputDto reserva) throws PlazasInsuficientes, ReservaNoEncontrada {
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
            reservaRepository.deleteById(reserva.getId());
            throw new PlazasInsuficientes();
        }
        return reserva;
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

    public List<ReservaDisponibleOutputDto> getPlazasDisponibles(HashMap<String, Object> condiciones){
        return reservaDisponibleRepository.consultarDisponibilidad(condiciones).stream().map(ReservaDisponible::reservaToReservaDisponibleOutputDto).toList();
    }
}
