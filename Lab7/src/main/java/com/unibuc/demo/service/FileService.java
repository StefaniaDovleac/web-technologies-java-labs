package com.unibuc.demo.service;

import com.unibuc.demo.domain.File;
import com.unibuc.demo.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File getById(long id){
        return this.fileRepository.getById(id);
    }

    public List<File> getAll(){
        return this.fileRepository.getAll();
    }

    public File save(File file){
        file.setId(new Random().nextLong());
        return this.fileRepository.save(file);
    }

//    public String delete(long id){
//        return
//    }
}
