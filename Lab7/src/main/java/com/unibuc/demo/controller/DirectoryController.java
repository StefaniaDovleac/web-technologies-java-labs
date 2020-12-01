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
    private final DirectoryService directoryService;
    private final DirectoryMapper directoryMapper;

    @Autowired
    public DirectoryController(DirectoryService directoryService, DirectoryMapper directoryMapper) {
        this.directoryService = directoryService;
        this.directoryMapper = directoryMapper;
    }

    @GetMapping("/{id}")
    public DirectoryDTO getById(@PathVariable Long id) {
        return directoryMapper.mapToDTO(directoryService.getById(id));
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DirectoryDTO> getAll() {
        return this.directoryService.getAll().stream().map(directory -> directoryMapper.mapToDTO(directory))
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DirectoryDTO> create(@RequestBody @Valid DirectoryDTO directoryDTO) {
        Directory directoryToSave = directoryMapper.mapToEntity(directoryDTO);
        DirectoryDTO savedDirectory = directoryMapper.mapToDTO(directoryService.save(directoryToSave));
        return new ResponseEntity<>(savedDirectory, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DirectoryDTO> update(@RequestBody DirectoryDTO directoryDTO) {
        Directory directoryToUpdate = directoryMapper.mapToEntity(directoryDTO);
        DirectoryDTO updatedDirectory = directoryMapper.mapToDTO(directoryService.update(directoryToUpdate));
        return new ResponseEntity<>(updatedDirectory,  HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public String delete(@PathVariable Long id) {
        return directoryService.delete(id);
    }
}
