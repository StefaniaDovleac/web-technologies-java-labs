package com.unibuc.demo.controller;

import com.unibuc.demo.domain.Directory;
import com.unibuc.demo.domain.File;
import com.unibuc.demo.dto.DirectoryDTO;
import com.unibuc.demo.dto.FileDTO;
import com.unibuc.demo.mapper.FileMapper;
import com.unibuc.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("files")
public class FileController {
    private final FileService service;
    private final FileMapper mapper;

    @Autowired
    public FileController(FileService service, FileMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileDTO> getById(@PathVariable Integer id) {
        FileDTO fileDTO = mapper.mapToDTO(service.getById(id));
        return new ResponseEntity<>(fileDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FileDTO> getAll() {
        return  this.service.getAll().stream().map(file -> mapper.mapToDTO(file))
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileDTO> create(@Valid @RequestBody FileDTO fileDTO) {
        File fileToInsert = mapper.mapToEntity(fileDTO);
        FileDTO fileDTOInserted = mapper.mapToDTO(this.service.save(fileToInsert));
        return new ResponseEntity<>(fileDTOInserted, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable Integer id){
         this.service.delete(id);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileDTO> update(@RequestBody FileDTO fileDTO) {
        File fileToUpdate = mapper.mapToEntity(fileDTO);
        FileDTO updatedfile = mapper.mapToDTO(service.update(fileToUpdate));
        service.update(fileToUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
