package com.unibuc.demo.service;

import com.unibuc.demo.domain.File;
import com.unibuc.demo.exceptions.EntityNotFoundException;
import com.unibuc.demo.repository.FileRepository;
import org.apache.commons.lang3.RandomStringUtils;
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
class FileServiceTest {
    @Mock
    private FileRepository repository;

    @InjectMocks
    private FileService service;

    private File expected;


    @BeforeEach
    void setUp() {
        expected = File.builder()
                .id(1)
                .title(RandomStringUtils.randomAlphabetic(30))
                .createdBy(1)
                .size(123)
                .content("aaaa45a45a8s78a")
                .isPublic(true)
                .lastModifiedBy(1)
                .lastModifiedOn(new Date())
                .createdOn(new Date())
                .parentId(1)
                .build();
    }

    @Test
    void testCreate() {

        //arrange
        File request = File.builder()
                .id(1)
                .title(RandomStringUtils.randomAlphabetic(30))
                .createdBy(1)
                .size(123)
                .content("aaaa45a45a8s78a")
                .isPublic(true)
                .lastModifiedBy(1)
                .lastModifiedOn(new Date())
                .createdOn(new Date())
                .parentId(1)
                .build();
        when(repository.save(request)).thenReturn(expected);
        //act
        File result = service.save(request);

        //assert
        assertThat(result).isEqualTo(expected);
        verify(repository, times(1)).save(request);
    }

    @Test
    void test_getById() {
        // Arrange
        Integer id = 13;
        expected.setId(id);

        when(repository.getById(id)).thenReturn(Optional.of(expected));

        // Act
        File result = service.getById(id);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void test_getById_throwsEntityNotFoundException_whenFileNotFound() {
        // Arrange
        Integer id = 16;

        when(repository.getById(id)).thenThrow(new EntityNotFoundException(String.format("File with id %d could not be found", id)));

        // Act
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.getById(id)
        );

        // Assert
        assertThat(exception.getMessage()).isEqualTo(String.format("File with id %d could not be found", id));
    }

    @Test
    void test_getAll() {
        // Arrange
        List<File> fileArrayList = new ArrayList<>();
        fileArrayList.add(File.builder()
                .id(1)
                .title(RandomStringUtils.randomAlphabetic(30))
                .createdBy(1)
                .size(123)
                .content("aaaa45a45a8s78a")
                .isPublic(true)
                .lastModifiedBy(1)
                .lastModifiedOn(new Date())
                .createdOn(new Date())
                .parentId(1)
                .build());

        fileArrayList.add(File.builder()
                .id(1)
                .title(RandomStringUtils.randomAlphabetic(20))
                .createdBy(3)
                .size(456)
                .content("sdsdsdsdaww")
                .isPublic(true)
                .lastModifiedBy(1)
                .lastModifiedOn(new Date())
                .createdOn(new Date())
                .parentId(1)
                .build());

        when(repository.getAll()).thenReturn(fileArrayList);

        // Act
        List<File> result = service.getAll();

        //Assert
        assertThat(result).isEqualTo(fileArrayList);
    }

    @Test
    void testUpdate() {

        //arrange
        File request = File.builder()
                .id(1)
                .title(RandomStringUtils.randomAlphabetic(30))
                .createdBy(1)
                .size(123)
                .content("aaaa45a45a8s78a")
                .isPublic(true)
                .lastModifiedBy(1)
                .lastModifiedOn(new Date())
                .createdOn(new Date())
                .parentId(1)
                .build();


        when(repository.updateFile(request)).thenReturn(request);
        //act
        File result = service.update(request);

        //assert
        assertThat(result).isEqualTo(request);
        verify(repository, times(1)).updateFile(request);
    }

    @Test
    void test_delete() {
        // Arrange
        Integer id = 10;
        Boolean expectedResult = true;

        when(repository.delete(id)).thenReturn(expectedResult);

        // Act
        Boolean result = service.delete(id);

        //Assert
        assertThat(result).isEqualTo(expectedResult);
        verify(repository, times(1)).delete(id);

    }

    @Test
    void test_delete_throwsEntityNotFoundException_whenFileNotFound() {
        // Arrange
        Integer id = 16;

        when(repository.delete(id)).thenThrow(new EntityNotFoundException(String.format("File with id %d could not be found", id)));

        // Act
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.delete(id)
        );

        // Assert
        assertThat(exception.getMessage()).isEqualTo(String.format("File with id %d could not be found", id));
    }
}