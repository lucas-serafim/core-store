package com.serafim.core_store.controller;

import com.serafim.core_store.dto.ExceptionResponseDTO;
import com.serafim.core_store.exception.CategoryAlreadyExistsException;
import com.serafim.core_store.exception.CategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    protected ResponseEntity<ExceptionResponseDTO> handlerCategoryAlreadyExists(CategoryAlreadyExistsException e) {
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(
                e.getMessage(),
                "CategoryAlreadyExistsException",
                Instant.now()
        );

        return new ResponseEntity<>(responseDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    protected ResponseEntity<ExceptionResponseDTO> handlerCategoryNotFound(CategoryNotFoundException e) {
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(
                e.getMessage(),
                "CategoryNotFoundException",
                Instant.now()
        );

        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<ExceptionResponseDTO> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(
//                e.getFieldError().getDefaultMessage(),
//                "MethodArgumentNotValidException",
//                Instant.now()
//        );
//
//        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
//    }
}
