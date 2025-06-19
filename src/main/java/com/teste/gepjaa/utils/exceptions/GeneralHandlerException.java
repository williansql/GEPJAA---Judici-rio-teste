package com.teste.gepjaa.utils.exceptions;

import com.teste.gepjaa.utils.models.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralHandlerException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<?>> notFoundException(NotFoundException e) {
        ApiResponse<?> response = new ApiResponse<>();
        response.of(HttpStatus.NOT_FOUND, e.getMessage(), null);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<?>> badRequestException(BadRequestException e) {
        ApiResponse<?> response = new ApiResponse<>();
        response.of(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
