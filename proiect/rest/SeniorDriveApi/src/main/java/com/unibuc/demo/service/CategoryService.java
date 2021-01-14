package com.unibuc.demo.service;

import com.unibuc.demo.domain.Category;
import com.unibuc.demo.domain.Directory;
import com.unibuc.demo.exceptions.EntityNotFoundException;
import com.unibuc.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category getById(Integer id) {
        return repository.getById(id).
                orElseThrow(() -> new EntityNotFoundException(String.format("Category with id %d could not be found", id)));
    }

    public List<Category> getAll(){
        return repository.getAll();
    }

    public Category save(Category category){
        return repository.save(category);
    }

    public Category update(Category category) {
        return repository.update(category);
    }

    public Boolean delete(Integer id) {
        Boolean result =  repository.delete(id);
        if(result == false){
            throw new EntityNotFoundException(String.format("Category with id %d could not be found", id));
        }
        return result;
    }
}
