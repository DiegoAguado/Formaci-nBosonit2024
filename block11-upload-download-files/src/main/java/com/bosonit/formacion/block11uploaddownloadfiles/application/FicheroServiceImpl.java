package com.bosonit.formacion.block11uploaddownloadfiles.application;

import com.bosonit.formacion.block11uploaddownloadfiles.controller.dto.FicheroOutputDto;
import com.bosonit.formacion.block11uploaddownloadfiles.domain.Fichero;
import com.bosonit.formacion.block11uploaddownloadfiles.exception.StorageException;
import com.bosonit.formacion.block11uploaddownloadfiles.exception.StorageFileNotFoundException;
import com.bosonit.formacion.block11uploaddownloadfiles.repository.FicheroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class FicheroServiceImpl implements FicheroService{
    private Path root = Paths.get("C:\\tmp");
    @Autowired
    FicheroRepository ficheroRepository;
    @Override
    public void setPath(String path) {
        this.root=Paths.get(path);
    }

    @Override
    public FicheroOutputDto store(MultipartFile file, String categoria) throws StorageException{
        try {
            String nombre = file.getOriginalFilename();
            String tipo = nombre.substring(nombre.indexOf(".")+1);
            if(!tipo.equals(categoria))
                throw new StorageException("Error al guardar el archivo");

            Path destination = this.root.resolve(Paths.get(file.getOriginalFilename())).normalize().toAbsolutePath();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destination, StandardCopyOption.REPLACE_EXISTING);

                return ficheroRepository.save(new Fichero(nombre, new Date(), categoria)).ficheroToFicheroOutputDto();
            }
        }catch (IOException e){
            throw new StorageException("Error al guardar el archivo");
        }
    }

    @Override
    public Stream<Path> loadAll() throws StorageException{
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new StorageException("Error al leer los ficheros guardados");
        }
    }

    @Override
    public Path load(String filename){
        return root.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) throws StorageFileNotFoundException{
        try {
            Path file = load(filename);

            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable())
                return resource;
            else
                throw new StorageFileNotFoundException("No se ha podido leer el fichero " + filename);
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("No se ha podido leer el fichero " + filename);
        }
    }

    @Override
    public FicheroOutputDto findById(int id) throws EntityNotFoundException{
        return ficheroRepository.findById(id).orElseThrow(EntityNotFoundException::new).ficheroToFicheroOutputDto();
    }

    @Override
    public void deleteAll(){
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public void init() throws StorageException{
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new StorageException("No se ha podido crear el directorio");
        }
    }
}
