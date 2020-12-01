package com.unibuc.demo.controller;

import com.unibuc.demo.domain.File;
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

@RestController
@RequestMapping("/files")
public class FileController {
    private final FileService fileService;
    private final FileMapper fileMapper;

    @Autowired
    public FileController(FileService fileService, FileMapper fileMapper) {
        this.fileService = fileService;
        this.fileMapper = fileMapper;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileDTO> getById(@PathVariable long id) {
        FileDTO fileDTO = fileMapper.mapToDTO(fileService.getById(id));
        return new ResponseEntity<>(fileDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FileDTO>> getAll() {
        List<FileDTO> fileDTOList = fileMapper.mapToDTO(fileService.getAll());
        return new ResponseEntity<>(fileDTOList, HttpStatus.OK);
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileDTO> create(@Valid @RequestBody FileDTO fileDTO) {
        File fileToInsert = fileMapper.mapToEntity(fileDTO);
        FileDTO fileDTOInserted = fileMapper.mapToDTO(this.fileService.save(fileToInsert));
        return new ResponseEntity<>(fileDTOInserted, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public String delete(@PathVariable Long id){
        return this.fileService.delete(id);
    }
}
