package com.bosonit.formacion.repository;

import com.bosonit.formacion.domain.Producto;
import com.bosonit.formacion.domain.Provincia;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, String> {
    @Query(value = "SELECT * FROM Producto WHERE UPPER(descripcion) LIKE UPPER(?1)", nativeQuery = true)
    Producto findProductoByNombreLike(String nombre);
}
