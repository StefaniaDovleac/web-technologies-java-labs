package com.unibuc.demo.repository;

import com.unibuc.demo.domain.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FileRepository {
    private List<File> files = new ArrayList<>();



    public List<File> getAll() {
        return files;
    }

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
