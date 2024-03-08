package com.bosonit.formacion.controller.Provincia;

import com.bosonit.formacion.application.Provincia.ProvinciaService;
import com.bosonit.formacion.controller.Cliente.dto.ClienteInputDto;
import com.bosonit.formacion.controller.Cliente.dto.ClienteOutputDto;
import com.bosonit.formacion.controller.Provincia.dto.ProvinciaInputDto;
import com.bosonit.formacion.controller.Provincia.dto.ProvinciaOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provincia")
public class ProvinciaController {
    @Autowired
    ProvinciaService provinciaService;

    @PostMapping
    public ResponseEntity<ProvinciaOutputDto> addProvincia(@RequestBody ProvinciaInputDto provincia) {
        return ResponseEntity.ok().body(provinciaService.addProvincia(provincia));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvinciaOutputDto> getProvinciaById(@PathVariable String id) throws EntityNotFoundException {
        return ResponseEntity.ok().body(provinciaService.getProvinciaById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProvinciaOutputDto>> getAllProvincias(@RequestParam int pageNumber, @RequestParam int pageSize){
        return ResponseEntity.ok().body(provinciaService.getAllProvincias(pageNumber, pageSize));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<ProvinciaOutputDto>> getAllProvinciasByNombre(@PathVariable String nombre, @RequestParam int pageNumber, @RequestParam int pageSize){
        return ResponseEntity.ok().body(provinciaService.getAllProvinciasByNombreLike(nombre, pageNumber, pageSize));
    }

    @PutMapping
    public ResponseEntity<ProvinciaOutputDto> updateProvincia(@RequestBody ProvinciaInputDto provincia) throws EntityNotFoundException{
        return ResponseEntity.ok().body(provinciaService.updateProvincia(provincia));
    }

    @DeleteMapping("/{id}")
    public void deleteProvinciaById(@PathVariable String id) throws EntityNotFoundException{
        provinciaService.deleteProvinciaById(id);
    }
}
