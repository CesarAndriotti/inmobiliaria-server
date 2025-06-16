package com.inmobiliaria.server.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.OperationType;
import com.inmobiliaria.server.services.OpertationType.OpertationTypeServiceImpl;
import jakarta.persistence.QueryTimeoutException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/operation-types")
public class OpertationTypeController {

    @Autowired
    Environment env;
    @Autowired
    OpertationTypeServiceImpl opertationTypeServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> postOpertationTypes(@RequestBody OperationType operationType) throws CustomException {
        
        if(operationType == null){

            throw new CustomException(env.getProperty("http.client.bad-request"), HttpStatus.BAD_REQUEST);
        }

        OperationType operationTypeRegistered = opertationTypeServiceImpl.registerOperationType(operationType);

        if (operationTypeRegistered == null) {
            throw new CustomException(env.getProperty("http.server.internal-server"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto<>(

            operationTypeRegistered,
            env.getProperty("http.success.created"),
            HttpStatus.CREATED.value()
        ));
    }

    @GetMapping("/show-list")
    public ResponseEntity<ResponseDto> getOpertationList() {
        
        try {
            List<OperationType> operationTypeListed = opertationTypeServiceImpl.getAllOpertationTypes();

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
                operationTypeListed,
                env.getProperty("http.success.ok"),
                HttpStatus.OK.value()
            ));
        }
        catch (QueryTimeoutException e) {
            
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new ResponseDto<>(
                env.getProperty("database.query-timeout"),
                HttpStatus.GATEWAY_TIMEOUT.value()
            ));
        } 
    }
}
