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
    public ResponseEntity<UserDTO> getBy(@PathVariable Long id) {
        UserDTO userDTO = mapper.mapToDTO(service.get(id));
        System.out.println(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody UserDTO userDTO) {
        service.create(mapper.mapToEntity(userDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> registerNewAccount(@Valid @RequestBody UserDTO userDTO) {
//        service.create(mapper.mapToEntity(userDTO));
//        return new ResponseEntity<>(HttpStatus.CREATED);

        try {
            User user = service.create(mapper.mapToEntity(userDTO));
        } catch (EmailExistsException ex) {


        }
//        UserDTO result = mapper.mapToDTO(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
