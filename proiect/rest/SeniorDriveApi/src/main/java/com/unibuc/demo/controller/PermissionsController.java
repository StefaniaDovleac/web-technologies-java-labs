package com.unibuc.demo.controller;

import com.unibuc.demo.domain.Permission;
import com.unibuc.demo.dto.PermissionDTO;
import com.unibuc.demo.mapper.PermissionMapper;
import com.unibuc.demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("permissions")
public class PermissionsController {
    private final PermissionService service;
    private final PermissionMapper mapper;

    @Autowired
    public PermissionsController(PermissionService service, PermissionMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PermissionDTO> getById(@PathVariable Integer id) {
        PermissionDTO permissionDTO = mapper.mapToDTO(service.getById(id));
        return new ResponseEntity<>(permissionDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PermissionDTO> getAll() {
        return this.service.getAll().stream().map(permission -> mapper.mapToDTO(permission))
                .collect(Collectors.toList());
    }

    @PostMapping(path="/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PermissionDTO> create (@RequestBody  @Valid PermissionDTO permissionDTO){
       Permission permissionToSave = mapper.mapToEntity(permissionDTO);
       PermissionDTO result = mapper.mapToDTO(service.save(permissionToSave));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PermissionDTO> update(@PathVariable Integer id, @RequestBody @Valid  PermissionDTO permissionDTO) {
        Permission permissionToUpdate = mapper.mapToEntity(permissionDTO);
        PermissionDTO updatedPermission = mapper.mapToDTO(service.update(permissionToUpdate));
        return new ResponseEntity<>(updatedPermission,  HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(true,  HttpStatus.OK);
    }

}
