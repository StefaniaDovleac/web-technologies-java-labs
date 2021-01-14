package com.unibuc.demo.service;

import com.unibuc.demo.domain.Permission;
import com.unibuc.demo.exceptions.DuplicateUserPermissionException;
import com.unibuc.demo.exceptions.EntityNotFoundException;
import com.unibuc.demo.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    private final PermissionRepository repository;

    @Autowired
    public PermissionService(PermissionRepository repository) {
        this.repository = repository;
    }

    public Permission getById(Integer id) {
        return repository.getById(id).
                orElseThrow(() -> new EntityNotFoundException(String.format("Permission with id %d could not be found", id)));
    }

    public List<Permission> getAll(){
        return repository.getAll();
    }

    public Permission save(Permission permission){
        if(repository.getByUserId(permission.getUserId()).isPresent()){
            throw  new DuplicateUserPermissionException("Duplicate user permissions");
        }
        return repository.save(permission);
    }

    public Permission update(Permission permission) {
        return repository.update(permission);
    }

    public Boolean delete(Integer id) {
        Boolean result =  repository.delete(id);
        if(result == false){
            throw new EntityNotFoundException(String.format("Permission with id %d could not be found", id));
        }
        return result;
    }
}
