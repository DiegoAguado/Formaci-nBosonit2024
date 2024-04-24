package com.formacion.backempresa.repository;

import com.formacion.backempresa.domain.ReservaDisponible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReservaDisponibleRepository extends JpaRepository<ReservaDisponible, Integer> {
    @Query(value = "SELECT * FROM Reserva_Disponible WHERE ciudad_Destino like ?1 and YEAR(fecha_Salida) = ?2 and MONTH(fecha_Salida) = ?3 and DAY(fecha_Salida) = ?4 and hora_Salida like ?5", nativeQuery = true)
    Optional<ReservaDisponible> findReservaDisponible(String ciudadDestino, Integer ano, Integer mes, Integer dia, Float horaSalida);
}
