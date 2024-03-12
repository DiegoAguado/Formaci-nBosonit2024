package com.bosonit.formacion.block11uploaddownloadfiles.repository;

import com.bosonit.formacion.block11uploaddownloadfiles.domain.Fichero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FicheroRepository extends JpaRepository<Fichero, Integer> {
}
