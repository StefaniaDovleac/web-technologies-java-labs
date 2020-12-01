package com.unibuc.demo.repository;

import com.unibuc.demo.domain.File;
import com.unibuc.demo.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FileRepository {
    private List<File> files = new ArrayList<>();

    public List<File> getAll() {
        return files;
    }

    public File getById(long id) {
        return files.stream()
                .filter(file -> file.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->new EntityNotFoundException(String.format("File with id could not be found", id)));
    }

    public File save(File file){
        files.add(file);
        return file;
    }

//    public String delete(long id){
//        File fileToDelete = getById(id);
//        if(fileToDelete)
//        files.remove(fileToDelete);
//    }

    @PostConstruct
    private void initList() {
        files.add(File.builder()
                .id(1L)
                .title("File1")
                .size(130)
                .build());

        files.add(File.builder()
                .id(2L)
                .title("File2")
                .size(320)
                .build());
    }
}
