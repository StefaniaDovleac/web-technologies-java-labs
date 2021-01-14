package com.unibuc.demo.controller;

import com.unibuc.demo.domain.Directory;
import com.unibuc.demo.dto.DirectoryDTO;
import com.unibuc.demo.mapper.DirectoryMapper;
import com.unibuc.demo.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/directories")
public class DirectoryController {
    private final DirectoryService service;
    private final DirectoryMapper mapper;

    @Autowired
    public DirectoryController(DirectoryService service, DirectoryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectoryDTO> getById(@PathVariable Integer id) {
        Directory directory = service.getById(id);
        return new ResponseEntity<>(mapper.mapToDTO(directory), HttpStatus.OK );
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DirectoryDTO>> getAll() {
        List<DirectoryDTO> directoryDTOS=  this.service.getAll().stream().map(directory -> mapper.mapToDTO(directory))
                .collect(Collectors.toList());
        return new ResponseEntity<>(directoryDTOS, HttpStatus.OK);
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DirectoryDTO> create(@RequestBody @Valid DirectoryDTO directoryDTO) {
        System.out.println("create");
        Directory directoryToSave = mapper.mapToEntity(directoryDTO);
        DirectoryDTO savedDirectory = mapper.mapToDTO(service.save(directoryToSave));
        return new ResponseEntity<>(savedDirectory, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DirectoryDTO> update(@RequestBody DirectoryDTO directoryDTO) {
        Directory directoryToUpdate = mapper.mapToEntity(directoryDTO);
        DirectoryDTO updatedDirectory = mapper.mapToDTO(service.update(directoryToUpdate));
        return new ResponseEntity<>(updatedDirectory,  HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(true,  HttpStatus.OK);
    }
}
