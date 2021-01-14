package com.unibuc.demo.service;

import com.unibuc.demo.domain.Directory;
import com.unibuc.demo.domain.File;
import com.unibuc.demo.domain.FileHistory;
import com.unibuc.demo.dto.FileDTO;
import com.unibuc.demo.enums.ActionType;
import com.unibuc.demo.exceptions.EntityNotFoundException;
import com.unibuc.demo.repository.FileHistoryRepository;
import com.unibuc.demo.repository.FileRepository;
import com.unibuc.demo.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class FileService {

    private final FileRepository fileRepository;
    private final StorageService storageService;
    private final FileHistoryService fileHistoryService;

    @Autowired
    public FileService(FileRepository fileRepository, StorageService storageService, FileHistoryService fileHistoryService) {
        this.fileRepository = fileRepository;
        this.storageService = storageService;
        this.fileHistoryService = fileHistoryService;
    }

    public File getById(Integer id) {
        return this.fileRepository.getById(id).orElseThrow(() -> new EntityNotFoundException(String.format("File with id %d could not be found", id)));
    }

    public List<File> getAll() {
        return this.fileRepository.getAll();
    }

    @Transactional
    public File save(File file) {
        String fileUri = storageService.uploadBlob(file.getTitle(), file.getContent().getBytes());
        file.setFileUri(fileUri);
        File savedFile = this.fileRepository.save(file);

        FileHistory history = FileHistory.builder()
                .fileId(savedFile.getId())
                .parentId(savedFile.getParentId())
                .title(savedFile.getTitle())
                .fileUri(savedFile.getFileUri())
                .modifiedBy(savedFile.getCreatedBy())
                .modifiedOn(savedFile.getCreatedOn())
                .size(savedFile.getSize())
                .action(ActionType.CREATED)
                .build();
        fileHistoryService.save(history);
        return savedFile;
    }

//    @Transactional
    public boolean delete(Integer id) {
//        String fileName = fileRepository.getById(id).get().getTitle();
//        storageService.deleteBlob(fileName);
       return  fileRepository.delete(id);
    }

    @Transactional
    public File update(File file) {
      File updatedFile = fileRepository.updateFile(file);
        FileHistory history = FileHistory.builder()
                .fileId(file.getId())
                .parentId(file.getParentId())
                .title(file.getTitle())
                .fileUri(file.getFileUri())
                .modifiedBy(file.getCreatedBy())
                .modifiedOn(file.getCreatedOn())
                .size(file.getSize())
                .action(ActionType.UPDATED)
                .build();
        fileHistoryService.save(history);
        return updatedFile;
    }
}
