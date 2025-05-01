package com.inmobiliaria.server.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  
@NoArgsConstructor 
public class ErrorResponseDto<T> {
    
    T data;
    String message;
    String error;
    int status;
    Date date;

    public ErrorResponseDto(T data, String message, int status, Date date) {
        this.data = data;
        this.message = message;
        this.status = status;
        this.date = date;
    }
}