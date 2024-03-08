package com.bosonit.formacion.repository;

import com.bosonit.formacion.domain.Cliente;
import com.bosonit.formacion.domain.Persona;
import com.bosonit.formacion.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    List<Cliente> findByNombreLike(String nombre);

    @Query(value = "Select Cliente.* from Cliente left join Provincia on Cliente.provincia = Provincia.codigo where UPPER(Provincia.nombre) Like UPPER(?1)", nativeQuery = true)
    List<Cliente> findByProvinciaLike(String provincia);
}
