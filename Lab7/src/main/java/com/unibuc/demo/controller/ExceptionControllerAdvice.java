package com.unibuc.demo.controller;

import com.unibuc.demo.dto.ErrorResponse;
import com.unibuc.demo.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j //logging din lombok
@ControllerAdvice //responsabila cu tratarea exceptiilor
public class ExceptionControllerAdvice {

    @ExceptionHandler
    protected  ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException exception){
       log.debug("Entity not found" + exception);
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .code(404)
                        .message(exception.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND);
    }
}
