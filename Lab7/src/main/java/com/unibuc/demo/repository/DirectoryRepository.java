package com.unibuc.demo.repository;

import com.unibuc.demo.domain.Directory;
import com.unibuc.demo.domain.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DirectoryRepository {
    private final List<Directory> directories = new ArrayList<>();
    private  FileRepository fileRepository;

    @Autowired
    public DirectoryRepository(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public DirectoryRepository() {
        initList();
    }

    public List<Directory> getAll(){
        return directories;
    }

    public Optional<Directory> getById(Long id){
        return directories.stream().filter(directory -> directory.getId().equals(id)).findFirst();
    }

    public Directory save( Directory directory){
        directories.add(directory);
        return directory;
    }

    public String delete(Long id){
        Optional<Directory> directoryToDelete = getById(id);
        if(directoryToDelete.isPresent()){
            directories.remove(directoryToDelete);
            return "Removed";
        }
        return "Directory with id " + id + " not found";
    }

    public Directory update(Directory directory){
         Optional<Directory> directoryToUpdate = getById(directory.getId());
         if(directoryToUpdate.isPresent()){
             directories.remove(directoryToUpdate);
             directories.add(directory);
             return getById(directory.getId()).get();
         }
         return null;
    }

    private void initList(){
        List<File> filesDirectory1 = fileRepository.getAll();

        directories.add(Directory.builder()
                .id(1L)
                .title("Directory1")
                .parentId(0L)
//                .files(filesDirectory1)
                .build());

        directories.add(Directory.builder()
                .id(2L)
                .title("Directory2")
                .parentId(1L)
//                .files(filesDirectory1)
                .build());
    }
}
