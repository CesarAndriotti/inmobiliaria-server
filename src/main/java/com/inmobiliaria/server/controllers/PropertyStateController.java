package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.PropertyState;
import com.inmobiliaria.server.services.PropertyState.PropertyStateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/propertystates")
public class PropertyStateController {

    @Autowired
    Environment env;
    @Autowired
    PropertyStateServiceImpl propertyStateServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> RegisterPropertyState(@RequestBody PropertyState propertystate) throws CustomException {
       
        if (propertystate == null || propertystate.getState() == "") {
            
            throw new CustomException(
                env.getProperty("http.client.bad-request"),
                HttpStatus.BAD_REQUEST 
            );
        }

        PropertyState registeredPropertyState = propertyStateServiceImpl.registerPropertyState(propertystate);
        
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(
            registeredPropertyState,
            env.getProperty("http.success.ok."),
            HttpStatus.OK.value()
        ));
    }
    
}
