package com.unibuc.lab10.controller;

import com.unibuc.lab10.domain.Address;
import com.unibuc.lab10.dto.AddressDto;
import com.unibuc.lab10.mapper.AddressMapper;
import com.unibuc.lab10.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("addresses")
public class AddressController {

    private final AddressMapper mapper;
    private final AddressService service;

    @Autowired
    public AddressController(AddressMapper mapper, AddressService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDto> create(@RequestBody AddressDto request) {
        Address address = service.create(mapper.toEntity(request));
        return new ResponseEntity<>(mapper.toDto(address), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDto> get(@PathVariable Long id) {
        Address address = service.getBy(id);
        return new ResponseEntity<>(mapper.toDto(address), HttpStatus.OK);
    }
}
