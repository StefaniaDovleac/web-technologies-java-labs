package com.unibuc.demo.service;

import com.unibuc.demo.domain.User;
import com.unibuc.demo.exceptions.EmailExistsException;
import com.unibuc.demo.exceptions.EntityNotFoundException;
import com.unibuc.demo.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.validator.constraints.Email;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    private User expected;


//    @Test
//    void testCreate() {
//
//        //arrange
//        User request = User.builder()
//                .id(1)
//                .userName("ad")
//                .email("ad@yahoo.com")
//                .isAdmin(true)
//                .password("ad")
//                .build();
//
//        User expected = User.builder()
//                .id(1)
//                .userName("ad")
//                .email("ad@yahoo.com")
//                .isAdmin(true)
//                .password("ad")
//                .build();
//        when(repository.findByEmail(any())).thenReturn(Optional.empty());
//        when(repository.save(request)).thenReturn(expected);
//        //act
//        User result =  service.create(request);
//
//        //assert
//        assertThat(result).isEqualTo(expected);
//        verify(repository, times(1)).save(request);
//    }
    @Test
    void test_create_throwsEmailExistsException() {
        User user = User.builder()
                .id(1)
                .userName("ad")
                .email("ad@yahoo.com")
                .isAdmin(true)
                .password("ad")
                .build();
        User savedUser = User.builder()
                .id(1)
                .userName("ad")
                .email("ad@yahoo.com")
                .isAdmin(true)
                .password("ad")
                .build();
        when(repository.findByEmail(any())).thenReturn(Optional.of(savedUser));

        EmailExistsException exception = assertThrows(EmailExistsException.class,
                () -> service.create(user));

        assertEquals("There is an account with that email address: "+ user.getEmail(),
                exception.getMessage());

        verify(repository).findByEmail(any());
        verify(repository, times(0)).save(any());
    }


    @Test
    void test_getById() {
        // Arrange
        Integer id = 10;
        this.expected = User.builder()
                .id(1)
                .userName("ad")
                .email("ad@yahoo.com")
                .isAdmin(true)
                .password("ad")
                .build();

        when(repository.findBy(id)).thenReturn(Optional.of(expected));

        // Act
        User result = service.get(id);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void test_getById_throwsEntityNotFoundException_whenUserNotFound() {
        // Arrange
        Integer id = 16;

        when(repository.findBy(id)).thenThrow(new EntityNotFoundException(String.format("User with id %d could not be found", id)));

        // Act
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.get(id)
        );

        // Assert
        assertThat(exception.getMessage()).isEqualTo(String.format("User with id %d could not be found", id));
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
    void test_delete_throwsEntityNotFoundException_whenUSerNotFound() {
        // Arrange
        Integer id = 16;

        when(repository.delete(id)).thenThrow(new EntityNotFoundException(String.format("User with id %d could not be found", id)));

        // Act
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                service.delete(id)
        );

        // Assert
        assertThat(exception.getMessage()).isEqualTo(String.format("User with id %d could not be found", id));
    }
}