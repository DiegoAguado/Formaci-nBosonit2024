package com.formacion.backweb.application;

import com.formacion.backweb.controller.dto.ReservaInputDto;
import com.formacion.backweb.controller.dto.ReservaOutputDto;

import java.util.HashMap;
import java.util.List;

public interface ReservaService {
    ReservaOutputDto add(ReservaInputDto reserva);
    ReservaOutputDto update(ReservaInputDto reserva);
    void delete(Integer id);
    List<ReservaOutputDto> getByCiudadDestino(HashMap<String, Object> condiciones);
}
