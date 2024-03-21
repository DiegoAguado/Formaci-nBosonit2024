package com.bosonit.formacion.controller.asignatura;

import com.bosonit.formacion.application.asignatura.AsignaturaService;
import com.bosonit.formacion.controller.asignatura.dto.AsignaturaInputDto;
import com.bosonit.formacion.controller.asignatura.dto.AsignaturaOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asignatura")
public class AsignaturaController {
    @Autowired
    AsignaturaService asignaturaService;

    @PostMapping
    public ResponseEntity<AsignaturaOutputDto> addAsignatura(@RequestBody AsignaturaInputDto asignatura){
        return ResponseEntity.ok().body(asignaturaService.addAsignatura(asignatura));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsignaturaOutputDto> getAsignaturaById(@RequestParam String id) throws EntityNotFoundException {
        return ResponseEntity.ok().body(asignaturaService.getAsignaturaById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AsignaturaOutputDto>> getAllAsignaturas(@RequestParam int pageNumber, @RequestParam int pageSize){
        return ResponseEntity.ok().body(asignaturaService.getAllAsignaturas(pageNumber,pageSize));
    }

    @GetMapping("/estudiante/{id}")
    public ResponseEntity<List<AsignaturaOutputDto>> getAllAsignaturasEstudiante(@PathVariable String id, @RequestParam int pageNumber, @RequestParam int pageSize){
        return ResponseEntity.ok().body(asignaturaService.getAllAsignaturasEstudiante(id, pageNumber, pageSize));
    }

    @PutMapping
    public ResponseEntity<AsignaturaOutputDto> updateAsignatura(@RequestBody AsignaturaInputDto asignatura) throws EntityNotFoundException{
        return ResponseEntity.ok().body(asignaturaService.updateAsignatura(asignatura));
    }

    @DeleteMapping("/{id}")
    public void deleteAsignaturaById(@PathVariable String id) throws EntityNotFoundException{
        asignaturaService.deleteAsignaturaById(id);
    }
}
