package com.serafim.core_store.controller;

import com.serafim.core_store.dto.ExceptionResponseDTO;
import com.serafim.core_store.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.List;

// TODO: add more exception cases
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

    @ExceptionHandler(ProductNotFoundException.class)
    protected ResponseEntity<ExceptionResponseDTO> handlerProductNotFoundException(ProductNotFoundException e) {
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(
                e.getMessage(),
                "ProductNotFoundException",
                Instant.now()
        );

        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SignInException.class)
    protected ResponseEntity<ExceptionResponseDTO> handlerSignInException(SignInException e) {
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(
                e.getMessage(),
                "SignInException",
                Instant.now()
        );

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    protected ResponseEntity<ExceptionResponseDTO> handlerEmailAlreadyExistException(EmailAlreadyExistException e) {
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(
                e.getMessage(),
                "EmailAlreadyExistException",
                Instant.now()
        );

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ExceptionResponseDTO> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<String> erros = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(
                erros,
                "MethodArgumentNotValidException",
                Instant.now()
        );

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
