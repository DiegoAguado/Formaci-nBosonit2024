package com.bosonit.formacion.block11uploaddownloadfiles.controller;

import com.bosonit.formacion.block11uploaddownloadfiles.application.FicheroService;
import com.bosonit.formacion.block11uploaddownloadfiles.controller.dto.FicheroOutputDto;
import com.bosonit.formacion.block11uploaddownloadfiles.exception.StorageException;
import com.bosonit.formacion.block11uploaddownloadfiles.exception.StorageFileNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

@Controller
public class FicheroController {
    @Autowired
    FicheroService ficheroService;

    @PostMapping("/upload")
    public ResponseEntity<FicheroOutputDto> uploadFile(@RequestParam MultipartFile file, @RequestParam String categoria) throws StorageException {
        return ResponseEntity.ok().body(ficheroService.store(file, categoria));
    }

    @GetMapping
    public ResponseEntity<Stream<Path>> listUploadedFiles(Model model) throws StorageException{
        return ResponseEntity.ok().body(ficheroService.loadAll());
    }

    @GetMapping("/filesByNombre/{filename}")
    public ResponseEntity<Resource> downloadFileByName(@PathVariable String filename)throws StorageFileNotFoundException {
        Resource file = ficheroService.loadAsResource(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/filesById/{id}")
    public ResponseEntity<Resource> downloadFileById(@PathVariable int id)throws StorageFileNotFoundException, EntityNotFoundException {
        Resource file = ficheroService.loadAsResource(ficheroService.findById(id).getNombre());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/setpath/")
    public ResponseEntity<String> setPath (@RequestParam String directorio) throws Exception {
        try{
            ficheroService.setPath(directorio);
            ficheroService.init();
            return ResponseEntity.ok().body("El nuevo directorio es: "+directorio);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
