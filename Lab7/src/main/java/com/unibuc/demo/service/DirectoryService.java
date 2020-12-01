package com.unibuc.demo.service;

import com.unibuc.demo.domain.Directory;
import com.unibuc.demo.dto.DirectoryDTO;
import com.unibuc.demo.exceptions.BadRequestException;
import com.unibuc.demo.mapper.DirectoryMapper;
import com.unibuc.demo.repository.DirectoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class DirectoryService {

    private final DirectoryRepository directoryRepository;

    public DirectoryService(DirectoryRepository directoryRepository) {
        this.directoryRepository = directoryRepository;
    }

    public Directory getById(Long id){
        return directoryRepository.getById(id);
    }

    public List<Directory> getAll(){
       return directoryRepository.getAll();
    }

    public Directory save(Directory directory){
        validateRequest(directory);
        directory.setId(new Random().nextLong());
        return directoryRepository.save(directory);
    }

    private void validateRequest(Directory directory) {
        if(!directoryRepository.getAll().stream().anyMatch(d->d.getId().equals(directory.getParentId()))){
            throw new BadRequestException("ParentId invalid");
        }
    }

//    public Directory update(Directory directory){
//        return directoryRepository.update(directory);
//    }
//
//    public String delete (Long id){
//        return directoryRepository.delete(id);
//    }
}
