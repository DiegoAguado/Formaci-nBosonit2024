package com.bosonit.formacion.Feign;

import com.bosonit.formacion.controller.Profesor.dto.ProfesorOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "profesor-feign", url = "http://localhost:8081/profesor")
public interface ProfesorFeign {
    @GetMapping("/{id}")
    public ResponseEntity<ProfesorOutputDto> getProfesorById(@PathVariable String id) throws EntityNotFoundException;
}
