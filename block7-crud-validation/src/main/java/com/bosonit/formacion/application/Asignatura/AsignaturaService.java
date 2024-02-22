package com.bosonit.formacion.application.Asignatura;

import com.bosonit.formacion.controller.Asignatura.dto.AsignaturaInputDto;
import com.bosonit.formacion.controller.Asignatura.dto.AsignaturaOutputDto;
import com.bosonit.formacion.customException.EntityNotFoundException;

import java.util.List;

public interface AsignaturaService {
    AsignaturaOutputDto addAsignatura(AsignaturaInputDto asignatura);
    AsignaturaOutputDto getAsignaturaById(String id) throws EntityNotFoundException;
    List<AsignaturaOutputDto> getAllAsignaturas(int pageNumber, int pageSize);
    List<AsignaturaOutputDto> getAllAsignaturasEstudiante(String id_student, int pageNumber, int pageSize);
    AsignaturaOutputDto updateAsignatura(AsignaturaInputDto asignatura) throws EntityNotFoundException;
    void deleteAsignaturaById(String id) throws EntityNotFoundException;
}
