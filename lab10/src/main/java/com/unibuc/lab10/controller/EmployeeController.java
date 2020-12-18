package com.unibuc.lab10.controller;

import com.unibuc.lab10.domain.Employee;
import com.unibuc.lab10.dto.EmployeeDto;
import com.unibuc.lab10.mapper.EmployeeMapper;
import com.unibuc.lab10.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeMapper mapper;
    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeMapper employeeMapper, EmployeeService employeeService) {
        this.mapper = employeeMapper;
        this.service = employeeService;
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto request) {
        Employee saved = service.create(mapper.toEntity(request));
        return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> get(@PathVariable Long id) {
        Employee saved = service.getBy(id);
        return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public void update(@PathVariable Long id, @RequestBody String name) {
        service.update(id, name);
    }
}
