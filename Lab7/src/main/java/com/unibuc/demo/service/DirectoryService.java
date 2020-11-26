package com.unibuc.demo.service;

import com.unibuc.demo.domain.Directory;
import com.unibuc.demo.dto.DirectoryDTO;
import com.unibuc.demo.mapper.DirectoryMapper;
import com.unibuc.demo.repository.DirectoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirectoryService {

    private final DirectoryRepository directoryRepository;

    public DirectoryService(DirectoryRepository directoryRepository, DirectoryMapper directoryMapper) {
        this.directoryRepository = directoryRepository;
        this.directoryMapper = directoryMapper;
    }

    private final DirectoryMapper directoryMapper;

    public Directory getById(Long id){
        return directoryRepository.getById(id).get();
    }

    public List<Directory> getAll(){
       return directoryRepository.getAll();
    }

    public Directory save(Directory directory){
        return directoryRepository.save(directory);
    }

    public Directory update(Directory directory){
        return directoryRepository.update(directory);
    }

    public String delete (Long id){
        return directoryRepository.delete(id);
    }
}
