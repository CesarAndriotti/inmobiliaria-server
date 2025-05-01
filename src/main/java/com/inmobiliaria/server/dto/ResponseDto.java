package com.inmobiliaria.server.dto;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
    
    T data;
    String message;
    String error;
    int status;
    Date date;

    public ResponseDto(String message, int status) {
        this.message = message;
        this.status = status;
        this.date = new Date();
    }

    public ResponseDto(T data, String message, int status) {
        this.data = data;
        this.message = message;
        this.status = status;
        this.date = new Date();
    }
}


