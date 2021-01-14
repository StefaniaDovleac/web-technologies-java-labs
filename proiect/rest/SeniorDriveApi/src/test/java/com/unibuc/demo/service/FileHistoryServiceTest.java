package com.unibuc.demo.service;

import com.unibuc.demo.domain.File;
import com.unibuc.demo.domain.FileHistory;
import com.unibuc.demo.enums.ActionType;
import com.unibuc.demo.exceptions.EntityNotFoundException;
import com.unibuc.demo.repository.FileHistoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileHistoryServiceTest {
    @Mock
    private FileHistoryRepository repository;

    @InjectMocks
    private FileHistoryService service;

    private FileHistory expected;


    @BeforeEach
    void setUp() {
        expected = FileHistory.builder()
                .id(1)
                .fileId(1)
                .revision(1)
                .action(ActionType.UPDATED)
                .modifiedOn(new Date())
                .modifiedBy(1)
                .size(1223)
                .title("File12")
                .parentId(48)
                .build();
    }

    @Test
    void testCreate() {

        //arrange
        FileHistory request = FileHistory.builder()
                .id(1)
                .fileId(1)
                .revision(1)
                .action(ActionType.UPDATED)
                .modifiedOn(new Date())
                .modifiedBy(1)
                .size(1223)
                .title("File12")
                .parentId(48)
                .build();
        when(repository.save(request)).thenReturn(expected);
        //act
        FileHistory result =  service.save(request);

        //assert
        assertThat(result).isEqualTo(expected);
        verify(repository, times(1)).save(request);
    }


    @Test
    void test_getByFileId() {
        // Arrange
        List<FileHistory> expectedPermissionList = new ArrayList<>();
        int fileId = 1;
        expectedPermissionList.add(FileHistory.builder()
                .id(1)
                .fileId(fileId)
                .revision(1)
                .action(ActionType.CREATED)
                .modifiedOn(new Date())
                .modifiedBy(1)
                .size(123)
                .title("File1")
                .parentId(48)
                .build());

        expectedPermissionList.add(FileHistory.builder()
                .id(1)
                .fileId(fileId)
                .revision(1)
                .action(ActionType.UPDATED)
                .modifiedOn(new Date())
                .modifiedBy(1)
                .size(1223)
                .title("File12")
                .parentId(48)
                .build());

        when(repository.getByFileId(fileId)).thenReturn(expectedPermissionList);

        // Act
        List<FileHistory> result = service.getByFileId(fileId);

        //Assert
        assertThat(result).isEqualTo(expectedPermissionList);
    }

}