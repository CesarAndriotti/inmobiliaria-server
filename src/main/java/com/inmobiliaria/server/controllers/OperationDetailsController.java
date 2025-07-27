package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/operation-details")
public class OperationDetailsController {

    @Autowired
    Environment env;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> postOperationDetails(@RequestBody OperationDetailsController operationDetails) throws CustomException {
        
        if (operationDetails == null) {
            
            throw new CustomException(
                env.getProperty("http.client.bad-request"),
                HttpStatus.BAD_REQUEST
            );
        }

        //
        
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto<>(
        
            "",
            HttpStatus.CREATED.value()  
        ));
    }
}
