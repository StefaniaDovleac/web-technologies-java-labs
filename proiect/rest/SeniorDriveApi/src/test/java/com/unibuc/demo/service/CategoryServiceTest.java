package com.unibuc.demo.service;

import com.unibuc.demo.domain.Category;
import com.unibuc.demo.domain.Directory;
import com.unibuc.demo.exceptions.EntityNotFoundException;
import com.unibuc.demo.repository.CategoryRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryService service;

    private Category expected;


    @BeforeEach
    void setUp() {
        expected = Category.builder()
                .id(1)
                .name(RandomStringUtils.randomAlphabetic(30))
                .description(RandomStringUtils.randomAlphabetic(30))
                .build();
    }

    @Test
    void testCreate() {

        //arrange
        Category request = Category.builder()
                .id(1)
                .name(RandomStringUtils.randomAlphabetic(30))
                .description(RandomStringUtils.randomAlphabetic(30))
                .build();
        when(repository.save(request)).thenReturn(expected);
        //act
        Category result =  service.save(request);

        //assert
        assertThat(result).isEqualTo(expected);
        verify(repository, times(1)).save(request);
    }

    @Test
    void test_getById() {
        // Arrange
        Integer id = 10;
        expected.setId(id);

        when(repository.getById(id)).thenReturn(Optional.of(expected));

        // Act
        Category result = service.getById(id);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void test_getById_throwsEntityNotFoundException_whenCategoryNotFound() {
        // Arrange
        Integer id = 16;

        when(repository.getById(id)).thenThrow(new EntityNotFoundException(String.format("Category with id %d could not be found", id)));

        // Act
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.getById(id)
        );

        // Assert
        assertThat(exception.getMessage()).isEqualTo(String.format("Category with id %d could not be found", id));
    }

    @Test
    void test_getAll() {
        // Arrange
        List<Category> expectedCategoryList = new ArrayList<>();
        expectedCategoryList.add(Category.builder()
                .id(1)
                .name(RandomStringUtils.randomAlphabetic(30))
                .description(RandomStringUtils.randomAlphabetic(30))
                .build());

        expectedCategoryList.add(Category.builder()
                .id(2)
                .name(RandomStringUtils.randomAlphabetic(20))
                .description(RandomStringUtils.randomAlphabetic(40))
                .build());

        when(repository.getAll()).thenReturn(expectedCategoryList);

        // Act
        List<Category> result = service.getAll();

        //Assert
        assertThat(result).isEqualTo(expectedCategoryList);
    }

    @Test
    void testUpdate() {

        //arrange
        Category request = Category.builder()
                .id(1)
                .name(RandomStringUtils.randomAlphabetic(30))
                .description(RandomStringUtils.randomAlphabetic(30))
                .build();

        Category expectedResponse = Category.builder()
                .id(1)
                .name(RandomStringUtils.randomAlphabetic(10))
                .description(RandomStringUtils.randomAlphabetic(20))
                .build();

        when(repository.update(request)).thenReturn(request);
        //act
        Category result =  service.update(request);

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

        when(repository.delete(id)).thenThrow(new EntityNotFoundException(String.format("Category with id %d could not be found", id)));

        // Act
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.delete(id)
        );

        // Assert
        assertThat(exception.getMessage()).isEqualTo(String.format("Category with id %d could not be found", id));
    }
}