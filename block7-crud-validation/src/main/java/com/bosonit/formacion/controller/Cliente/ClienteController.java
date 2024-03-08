package com.bosonit.formacion.controller.Cliente;

import com.bosonit.formacion.application.Cliente.ClienteService;
import com.bosonit.formacion.controller.Cliente.dto.ClienteInputDto;
import com.bosonit.formacion.controller.Cliente.dto.ClienteOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteOutputDto> addCliente(@RequestBody ClienteInputDto cliente) throws EntityNotFoundException{
        return ResponseEntity.ok().body(clienteService.addCliente(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteOutputDto> getClienteById(@PathVariable String id) throws EntityNotFoundException {
        return ResponseEntity.ok().body(clienteService.getClienteById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClienteOutputDto>> getAllClientes(@RequestParam int pageNumber, @RequestParam int pageSize){
        return ResponseEntity.ok().body(clienteService.getAllClientes(pageNumber, pageSize));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<ClienteOutputDto>> getAllClientesByNombre(@PathVariable String nombre, @RequestParam int pageNumber, @RequestParam int pageSize){
        return ResponseEntity.ok().body(clienteService.getAllClientesByNombreLike(nombre, pageNumber, pageSize));
    }

    @GetMapping("/provincia/{provincia}")
    public ResponseEntity<List<ClienteOutputDto>> getAllClientesByProvincia(@PathVariable String provincia, @RequestParam int pageNumber, @RequestParam int pageSize){
        return ResponseEntity.ok().body(clienteService.getAllClientesByProvinciaLike(provincia, pageNumber, pageSize));
    }

    @PutMapping
    public ResponseEntity<ClienteOutputDto> updateCliente(@RequestBody ClienteInputDto cliente) throws EntityNotFoundException{
        return ResponseEntity.ok().body(clienteService.updateCliente(cliente));
    }

    @DeleteMapping("/{id}")
    public void deleteClienteById(@PathVariable String id) throws EntityNotFoundException{
        clienteService.deleteClienteById(id);
    }
}
