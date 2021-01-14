package com.unibuc.demo.service;

import com.unibuc.demo.domain.Permission;
import com.unibuc.demo.exceptions.EntityNotFoundException;
import com.unibuc.demo.repository.PermissionRepository;
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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PermissionServiceTest {
    @Mock
    private PermissionRepository repository;

    @InjectMocks
    private PermissionService service;

    private Permission expected;


    @BeforeEach
    void setUp() {
        expected = Permission.builder()
                .id(1)
                .userId(1)
                .createRight(false)
                .deleteRight(true)
                .downloadRight(true)
                .moveRight(false)
                .renameRight(false)
                .uploadRight(true)
                .build();
    }

    @Test
    void testCreate() {

        //arrange
        Permission request = Permission.builder()
                .id(1)
                .userId(1)
                .createRight(true)
                .deleteRight(true)
                .downloadRight(false)
                .moveRight(false)
                .renameRight(false)
                .uploadRight(true)
                .build();
        when(repository.save(request)).thenReturn(expected);
        //act
        Permission result =  service.save(request);

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
        Permission result = service.getById(id);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void test_getById_throwsEntityNotFoundException_whenPermissionNotFound() {
        // Arrange
        Integer id = 16;

        when(repository.getById(id)).thenThrow(new EntityNotFoundException(String.format("Permission with id %d could not be found", id)));

        // Act
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.getById(id)
        );

        // Assert
        assertThat(exception.getMessage()).isEqualTo(String.format("Permission with id %d could not be found", id));
    }

    @Test
    void test_getAll() {
        // Arrange
        List<Permission> expectedPermissionList = new ArrayList<>();
        expectedPermissionList.add(Permission.builder()
                .id(1)
                .userId(1)
                .createRight(true)
                .deleteRight(true)
                .downloadRight(false)
                .moveRight(false)
                .renameRight(false)
                .uploadRight(false)
                .build());

        expectedPermissionList.add(Permission.builder()
                .id(1)
                .userId(1)
                .createRight(true)
                .deleteRight(false)
                .downloadRight(false)
                .moveRight(false)
                .renameRight(false)
                .uploadRight(true)
                .build());

        when(repository.getAll()).thenReturn(expectedPermissionList);

        // Act
        List<Permission> result = service.getAll();

        //Assert
        assertThat(result).isEqualTo(expectedPermissionList);
    }

    @Test
    void testUpdate() {

        //arrange
        Permission request = Permission.builder()
                .id(1)
                .userId(1)
                .createRight(true)
                .deleteRight(true)
                .downloadRight(false)
                .moveRight(false)
                .renameRight(false)
                .uploadRight(true)
                .build();

        when(repository.update(request)).thenReturn(request);
        //act
        Permission result =  service.update(request);

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
    void test_delete_throwsEntityNotFoundException_whenPermissionNotFound() {
        // Arrange
        Integer id = 16;

        when(repository.delete(id)).thenThrow(new EntityNotFoundException(String.format("Permission with id %d could not be found", id)));

        // Act
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.delete(id)
        );

        // Assert
        assertThat(exception.getMessage()).isEqualTo(String.format("Permission with id %d could not be found", id));
    }

}