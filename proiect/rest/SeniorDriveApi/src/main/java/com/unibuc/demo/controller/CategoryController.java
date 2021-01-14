package com.unibuc.demo.controller;

import com.unibuc.demo.domain.Category;
import com.unibuc.demo.domain.Directory;
import com.unibuc.demo.dto.CategoryDTO;
import com.unibuc.demo.dto.DirectoryDTO;
import com.unibuc.demo.mapper.CategoryMapper;
import com.unibuc.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("categories")
public class CategoryController {
    private final CategoryService service;
    private final CategoryMapper mapper;

    @Autowired
    public CategoryController(CategoryService service, CategoryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(path = "/getBy")
    public ResponseEntity<CategoryDTO> getById(@RequestParam Integer id) {
        CategoryDTO categoryDTO = mapper.mapToDTO(service.getById(id));
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDTO> getAll() {
        return this.service.getAll().stream().map(category -> mapper.mapToDTO(category))
                .collect(Collectors.toList());
    }

    @PostMapping(path="/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> create (@RequestBody  @Valid CategoryDTO categoryDTO){
       Category categoryToSave = mapper.mapToEntity(categoryDTO);
       CategoryDTO result = mapper.mapToDTO(service.save(categoryToSave));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> update(@RequestBody @Valid  CategoryDTO categoryDTO) {
        Category categoryToUpdate = mapper.mapToEntity(categoryDTO);
        CategoryDTO updatedCategory = mapper.mapToDTO(service.update(categoryToUpdate));
        return new ResponseEntity<>(updatedCategory,  HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(true,  HttpStatus.OK);
    }

}
