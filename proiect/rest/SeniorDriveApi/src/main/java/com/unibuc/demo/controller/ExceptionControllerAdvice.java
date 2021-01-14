package com.unibuc.demo.controller;

import com.unibuc.demo.dto.ErrorResponse;
import com.unibuc.demo.exceptions.BadRequestException;
import com.unibuc.demo.exceptions.DuplicateUserPermissionException;
import com.unibuc.demo.exceptions.EmailExistsException;
import com.unibuc.demo.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@Slf4j //logging din lombok
@ControllerAdvice //responsabila cu tratarea exceptiilor
public class ExceptionControllerAdvice {

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException exception) {
        log.debug("Entity not found" + exception);
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .code(404)
                        .message(exception.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException exception) {
        log.debug("Bad request " + exception);
        return new ResponseEntity<>(ErrorResponse.builder()
                .code(400)
                .message(exception.getMessage())
                .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleDuplicateUserPermissions(DuplicateUserPermissionException exception) {
        log.debug("Bad request " + exception);
        return new ResponseEntity<>(ErrorResponse.builder()
                .code(400)
                .message(exception.getMessage())
                .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        log.debug("Invalid request" + exception);
        String invalidFieldsMessage = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField())
                .collect(Collectors.joining(","));
        return new ResponseEntity<>(ErrorResponse.builder()
                .code(400)
                .message("The following fields are invalid: " + invalidFieldsMessage)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleEmailExistsException(EmailExistsException exception){
        return new ResponseEntity<>(ErrorResponse.builder()
                .code(400)
                .message("Email exists")
                .build(), HttpStatus.BAD_REQUEST);
    }
}
