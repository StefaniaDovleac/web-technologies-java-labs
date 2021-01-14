package com.unibuc.demo.controller;

import com.unibuc.demo.domain.User;
import com.unibuc.demo.dto.UserDTO;
import com.unibuc.demo.exceptions.EmailExistsException;
import com.unibuc.demo.mapper.UserMapper;
import com.unibuc.demo.mapper.UserMapperImpl;
import com.unibuc.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @Autowired
    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<UserDTO> getBy(@PathVariable Integer id) {
        UserDTO userDTO = mapper.mapToDTO(service.get(id));
        System.out.println(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> registerNewAccount(@Valid @RequestBody UserDTO userDTO) {
        UserDTO  dto = mapper.mapToDTO(service.create(mapper.mapToEntity(userDTO)));
        return new ResponseEntity<>( dto, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(true,  HttpStatus.OK);
    }
}
