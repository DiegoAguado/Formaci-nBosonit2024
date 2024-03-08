package com.bosonit.formacion.application.Provincia;

import com.bosonit.formacion.controller.Provincia.dto.ProvinciaInputDto;
import com.bosonit.formacion.controller.Provincia.dto.ProvinciaOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;

import java.util.List;

public interface ProvinciaService {
    ProvinciaOutputDto addProvincia(ProvinciaInputDto provincia);
    ProvinciaOutputDto getProvinciaById(String codigo) throws EntityNotFoundException;
    List<ProvinciaOutputDto> getAllProvincias(int pageNumber, int pageSize);
    List<ProvinciaOutputDto> getAllProvinciasByNombreLike(String nombre, int pageNumber, int pageSize);
    ProvinciaOutputDto updateProvincia(ProvinciaInputDto provincia) throws EntityNotFoundException;
    void deleteProvinciaById(String codigo) throws EntityNotFoundException;
}
