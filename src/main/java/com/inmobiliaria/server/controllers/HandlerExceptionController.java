package com.inmobiliaria.server.controllers;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.inmobiliaria.server.dto.ResponseDto;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ResponseDto> internalServerError (Exception ex){

        ResponseDto errorDto = new ResponseDto();
        errorDto.setDate(new Date());
        errorDto.setError(null);
        errorDto.setMessage("No encontrado");
        errorDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.internalServerError().body(errorDto);
    }
}
