package com.formacion.backweb.repository;

import com.formacion.backweb.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.HashMap;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByCiudadDestino(String ciudadDestino);

    List<Reserva> getByCiudadDestino(HashMap<String, Object> conditions);
}
