package com.inmobiliaria.server.controllers;

import java.util.Date;

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

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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


        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto<>(

            operationTypeRegistered,
            env.getProperty("http.success.created"),
            HttpStatus.CREATED.value()
        ));
    }
}
