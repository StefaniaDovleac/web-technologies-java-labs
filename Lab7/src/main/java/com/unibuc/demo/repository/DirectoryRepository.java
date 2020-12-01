package com.unibuc.demo.repository;

import com.unibuc.demo.domain.Directory;
import com.unibuc.demo.domain.File;
import com.unibuc.demo.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class DirectoryRepository {
    private final List<Directory> directories = new ArrayList<>();
    private FileRepository fileRepository;

    @Autowired
    public DirectoryRepository(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public DirectoryRepository() {
        initList();
    }

    public List<Directory> getAll() {
        return directories;
    }

    public Directory getById(Long id) {
        return directories.stream()
                .filter(directory -> directory.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new EntityNotFoundException(String.format("Directory with id %d could not be found", id)));

    }

    public Directory save(Directory directory) {
        directories.add(directory);
        return directory;
    }

    public String delete(Long id) {
        Directory directoryToDelete = getById(id);
        directories.remove(directoryToDelete);
        return "Removed";
    }

    public Directory update(Directory directory) {
        Directory directoryToUpdate = getById(directory.getId());
        directories.remove(directoryToUpdate);
        directories.add(directory);
        return getById(directory.getId());
    }

    @PostConstruct
    private void initList() {

        directories.add(Directory.builder()
                .id(1L)
                .title("Directory1")
                .parentId(0L)
                .files(Collections.singletonList(File.builder()
                        .id(2L)
                        .title("File2")
                        .size(320)
                        .build()))
                .build());

        directories.add(Directory.builder()
                .id(2L)
                .title("Directory2")
                .parentId(1L)
                .files(null)
                .build());
    }
}
