package com.bosonit.formacion.block11uploaddownloadfiles.application;

import com.bosonit.formacion.block11uploaddownloadfiles.controller.dto.FicheroOutputDto;
import com.bosonit.formacion.block11uploaddownloadfiles.exception.StorageException;
import com.bosonit.formacion.block11uploaddownloadfiles.exception.StorageFileNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FicheroService {
    void setPath(String path);
    FicheroOutputDto store(MultipartFile file, String categoria)throws StorageException;
    Stream<Path> loadAll() throws StorageException;
    Path load(String filename);
    Resource loadAsResource(String filename)throws StorageFileNotFoundException;
    FicheroOutputDto findById(int id) throws EntityNotFoundException;
    void deleteAll();
    void init() throws StorageException;
}
