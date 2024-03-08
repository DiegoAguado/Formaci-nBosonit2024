package com.bosonit.formacion.repository;

import com.bosonit.formacion.domain.Provincia;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface ProvinciaRepository extends JpaRepository<Provincia, String> {
    List<Provincia> findProvinciaByNombreLike(String nombre, PageRequest pageRequest);
}
