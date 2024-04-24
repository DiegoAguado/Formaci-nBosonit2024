package com.formacion.backweb.application;

import com.formacion.backweb.controller.dto.ReservaInputDto;
import com.formacion.backweb.controller.dto.ReservaOutputDto;
import com.formacion.backweb.domain.Reserva;
import com.formacion.backweb.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService{
    @Autowired
    ReservaRepository reservaRepository;

    @Override
    public ReservaOutputDto add(ReservaInputDto reservaInput) {
        return reservaRepository.save(new Reserva(reservaInput)).reservaToReservaOutputDto();
    }

    @Override
    public ReservaOutputDto update(ReservaInputDto reserva) {
        reservaRepository.findById(reserva.getId()).orElseThrow();
        return reservaRepository.save(new Reserva(reserva)).reservaToReservaOutputDto();
    }

    @Override
    public void delete(Integer id) {
        reservaRepository.findById(id).orElseThrow();
        reservaRepository.deleteById(id);
    }

    @Override
    public List<ReservaOutputDto> getByCiudadDestino(HashMap<String, Object> condiciones) {
        return reservaRepository.getByCiudadDestino(condiciones).stream().map(Reserva::reservaToReservaOutputDto).toList();
    }
}
