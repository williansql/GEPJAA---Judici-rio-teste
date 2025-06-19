package com.teste.gepjaa.utils.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private int status = HttpStatus.OK.value();
    private String message = "";
    private T data = null;

    public ApiResponse<T> of(HttpStatus httpStatus, String message, T data) {
        this.status = httpStatus.value();
        this.data = data;
        this.message = message;
        return this;
    }
}