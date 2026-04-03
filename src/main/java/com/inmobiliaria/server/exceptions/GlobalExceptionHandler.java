package com.inmobiliaria.server.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandlerOld.class);

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionDetails> handleCustomException(CustomException e) {

        HttpStatus status = e.getHttpStatus();

        LOG.error("TECHNICAL MESSAGE: {}", e.getTechnicalMessage(), e);

        ExceptionDetails exceptionDetails = new ExceptionDetails("ERROR", e.getTechnicalMessage());

        return ResponseEntity
                .status(status)
                .body(exceptionDetails);
    }
}
