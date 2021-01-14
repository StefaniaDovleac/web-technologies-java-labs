package com.unibuc.demo.service;

import com.unibuc.demo.domain.Directory;
import com.unibuc.demo.exceptions.EntityNotFoundException;
import com.unibuc.demo.repository.DirectoryRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.JsonPathAssertions;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DirectoryServiceTest {

    @Mock
    private DirectoryRepository repository;

    @InjectMocks // ce vreau sa testez
    private DirectoryService service;

    private Directory expected;

    @BeforeEach
    void setUp() {
        expected = Directory.builder()
                .id(1)
                .title(RandomStringUtils.randomAlphabetic(30))
                .createdBy(1)
                .createdOn(new Date())
                .lastModifiedBy(1)
                .lastModifiedOn(new Date())
                .categoryId(1)
                .build();
    }

    @Test
    void testCreate() {

        //arrange
       Directory request = Directory.builder()
                .id(1)
                .title(RandomStringUtils.randomAlphabetic(30))
                .createdBy(1)
                .createdOn(new Date())
                .lastModifiedBy(1)
                .lastModifiedOn(new Date())
                .categoryId(1)
                .build();
        when(repository.save(request)).thenReturn(expected);
        //act
        Directory result =  service.save(request);

        //assert
        assertThat(result).isEqualTo(expected);
        verify(repository, times(1)).save(request);
    }



    @Test
    void test_getById() {
        // Arrange
        Integer id = 123;
        expected.setId(id);

        when(repository.getById(id)).thenReturn(Optional.of(expected));

        // Act
        Directory result = service.getById(id);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void test_getById_throwsEntityNotFoundException_whenDirectoryNotFound() {
        // Arrange
        Integer id = 10;

        when(repository.getById(id)).thenThrow(new EntityNotFoundException(String.format("Directory with id %d could not be found", id)));

        // Act
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.getById(id)
        );

        // Assert
        assertThat(exception.getMessage()).isEqualTo(String.format("Directory with id %d could not be found", id));
    }

    @Test
    void test_getAll() {
        // Arrange
        List<Directory> expectedDirectoryList = new ArrayList<>();
        expectedDirectoryList.add(Directory.builder()
                .id(1)
                .title(RandomStringUtils.randomAlphabetic(20))
                .createdBy(1)
                .createdOn(new Date())
                .lastModifiedBy(1)
                .lastModifiedOn(new Date())
                .categoryId(1)
                .build());

        expectedDirectoryList.add(Directory.builder()
                .id(2)
                .title(RandomStringUtils.randomAlphabetic(14))
                .createdBy(1)
                .createdOn(new Date())
                .lastModifiedBy(1)
                .lastModifiedOn(new Date())
                .categoryId(1)
                .build());

        when(repository.getAll()).thenReturn(expectedDirectoryList);

        // Act
        List<Directory> result = service.getAll();

        //Assert
        assertThat(result).isEqualTo(expectedDirectoryList);
    }


    @Test
    void testUpdate() {

        //arrange
        Directory request = Directory.builder()
                .id(2)
                .title(RandomStringUtils.randomAlphabetic(14))
                .createdBy(1)
                .createdOn(new Date())
                .lastModifiedBy(1)
                .lastModifiedOn(new Date())
                .categoryId(1)
                .build();



        when(repository.update(request)).thenReturn(request);
        //act
        Directory result =  service.update(request);

        //assert
        assertThat(result).isEqualTo(request);
        verify(repository, times(1)).update(request);
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
    }

    @Test
    void test_delete_throwsEntityNotFoundException_whenCategoryNotFound() {
        // Arrange
        Integer id = 16;

        when(repository.delete(id)).thenThrow(new EntityNotFoundException(String.format("Directory with id %d could not be found", id)));

        // Act
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.delete(id)
        );

        // Assert
        assertThat(exception.getMessage()).isEqualTo(String.format("Directory with id %d could not be found", id));
    }
}