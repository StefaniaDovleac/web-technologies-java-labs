package com.unibuc.demo.service;

import com.unibuc.demo.domain.FileHistory;
import com.unibuc.demo.repository.FileHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileHistoryService {

    private final FileHistoryRepository repository;

    public FileHistoryService(FileHistoryRepository repository) {
        this.repository = repository;
    }

    public List<FileHistory> getByFileId(Integer fileId){
        return repository.getByFileId(fileId);
    }

    public FileHistory save(FileHistory fileHistory){
        return repository.save(fileHistory);
    }
}
