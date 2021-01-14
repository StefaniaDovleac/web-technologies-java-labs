package com.unibuc.demo.controller;

import com.unibuc.demo.domain.FileHistory;
import com.unibuc.demo.dto.FileHistoryDTO;
import com.unibuc.demo.mapper.FileHistoryMapper;
import com.unibuc.demo.service.FileHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("file-history")
public class FileHistoryController {
    private final FileHistoryService service;
    private final FileHistoryMapper mapper;

    @Autowired
    public FileHistoryController(FileHistoryService service, FileHistoryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(path = "/getByFileId/{fileId}")
    public ResponseEntity<List<FileHistoryDTO>> getById(@PathVariable Integer fileId) {
        List<FileHistoryDTO> fileHistoryDTOList = this.service.getByFileId(fileId).stream().map(file -> mapper.mapToDTO(file))
                .collect(Collectors.toList());
        return new ResponseEntity<>(fileHistoryDTOList, HttpStatus.OK);
    }

    @PostMapping(path="/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileHistoryDTO> create (@RequestBody  @Valid FileHistoryDTO fileHistoryDTO){
        FileHistory categoryToSave = mapper.mapToEntity(fileHistoryDTO);
        FileHistoryDTO result = mapper.mapToDTO(service.save(categoryToSave));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
