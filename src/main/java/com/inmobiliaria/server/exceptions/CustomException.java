package com.inmobiliaria.server.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception{

    private String technicalMessage;
    private ExceptionDetails exceptionDetails;
    private HttpStatus httpStatus;

    public CustomException(String message, HttpStatus httpStatus) {
        super(message);
        setTechnicalMessage(message);
        setHttpStatus(httpStatus);
    }

    public String getTechnicalMessage() {
        return technicalMessage;
    }

    public void setTechnicalMessage(String message) {
        this.technicalMessage = message;
    }

    public ExceptionDetails getExceptionDetails() {
        return exceptionDetails;
    }

    public void setExceptionDetails(ExceptionDetails exceptionDetails) {
        this.exceptionDetails = exceptionDetails;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
