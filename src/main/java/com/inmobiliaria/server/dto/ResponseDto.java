package com.inmobiliaria.server.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  
@NoArgsConstructor 
public class ResponseDto {
    
    String message;
    String error;
    int status;
    Date date;
}
