package com.inmobiliaria.server.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@PropertySource("classpath:messages.properties")
public class GlobalExceptionHandler {

    @Autowired
    private Environment env;
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionDetails> handleBadRequest(CustomException e) {

        //Desmembramos el custom exception
        int statusCode = e.getHttpStatus().value();
        String message = "";

        switch (statusCode) {
        
            case 200: message = env.getProperty("http.success.ok"); break;
            case 201: message = env.getProperty("http.success.created"); break;
            case 202: message = env.getProperty("http.success.accepted"); break;
            case 204: message = env.getProperty("http.success.no-content"); break;
            case 206: message = env.getProperty("http.success.partial-content"); break;
            case 300: message = env.getProperty("http.redirect.multiple-choices"); break;
            case 301: message = env.getProperty("http.redirect.moved-permanently"); break;
            case 302: message = env.getProperty("http.redirect.found"); break;
            case 304: message = env.getProperty("http.redirect.not-modified"); break;
            case 307: message = env.getProperty("http.redirect.temporary-redirect"); break;
            case 308: message = env.getProperty("http.redirect.permanent-redirect"); break;
            case 400: message = env.getProperty("http.client.bad-request"); break;
            case 401: message = env.getProperty("http.client.unauthorized"); break;
            case 403: message = env.getProperty("http.client.forbidden"); break;
            case 404: message = env.getProperty("http.client.not-found"); break;
            case 405: message = env.getProperty("http.client.method-not-allowed"); break;
            case 409: message = env.getProperty("http.client.conflict"); break;
            case 415: message = env.getProperty("http.client.unsupported-media-type"); break;
            case 429: message = env.getProperty("http.client.too-many-requests"); break;
            case 500: message = env.getProperty("http.server.internal-server"); break;
            case 501: message = env.getProperty("http.server.not-implemented"); break;
            case 502: message = env.getProperty("http.server.bad-gateway"); break;
            case 503: message = env.getProperty("http.server.service-unavailable"); break;
            case 504: message = env.getProperty("http.server.gateway-timeout"); break;

            default: message = env.getProperty("runtime-exception", "Unexpected error"); break;
        }
        
        //El exception details va a tener el mensaje para el cliente
        LOG.error("\n\nTECHNICAL MESSAGE: "+e.getTechnicalMessage()+"\n", e);
        ExceptionDetails exceptionDetails = new ExceptionDetails("ERROR", message);
        e.setExceptionDetails(exceptionDetails);
        return ResponseEntity.status(e.getHttpStatus()).body(exceptionDetails);
    }
}

