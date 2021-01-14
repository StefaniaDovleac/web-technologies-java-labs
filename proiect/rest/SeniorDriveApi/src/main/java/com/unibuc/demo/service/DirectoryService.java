package com.unibuc.demo.service;

import com.unibuc.demo.domain.Directory;
import com.unibuc.demo.exceptions.BadRequestException;
import com.unibuc.demo.exceptions.EntityNotFoundException;
import com.unibuc.demo.repository.DirectoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DirectoryService {

    private final DirectoryRepository directoryRepository;

    public DirectoryService(DirectoryRepository directoryRepository) {
        this.directoryRepository = directoryRepository;
    }

    public Directory getById(Integer id) {
        return directoryRepository.getById(id).
        orElseThrow(() -> new EntityNotFoundException(String.format("Directory with id %d could not be found", id)));
    }

    public List<Directory> getAll() {
        return directoryRepository.getAll();
    }

    public Directory save(Directory directory) {
//        validateRequest(directory);
        return directoryRepository.save(directory);
    }

//    private void validateRequest(Directory directory) {
//        if (!directoryRepository.getAll().stream().anyMatch(d -> d.getId().equals(directory.getParentId()))) {
//            throw new BadRequestException("ParentId invalid");
//        }
//    }

    public Directory update(Directory directory) {
        return directoryRepository.update(directory);
    }

    public boolean delete(Integer id) {
        return directoryRepository.delete(id);
    }
}
