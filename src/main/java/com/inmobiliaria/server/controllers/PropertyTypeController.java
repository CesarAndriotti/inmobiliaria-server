package com.inmobiliaria.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.PropertyType;
import com.inmobiliaria.server.services.PropertyType.PropertyTypeServiceImpl;

@RestController
@RequestMapping("/api/property-types")
public class PropertyTypeController {

    @Autowired
    Environment env;
    @Autowired
    PropertyTypeServiceImpl propertyTypeServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> postPropertyState(@RequestBody PropertyType propertytype) throws CustomException {
       
        if (propertytype == null ){
            
            throw new CustomException(
                env.getProperty("http.client.bad-request"),
                HttpStatus.BAD_REQUEST 
            );
        }

        PropertyType registeredPropertyState = propertyTypeServiceImpl.registerPropertyType(propertytype);
        
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
            registeredPropertyState,
            env.getProperty("http.success.created"),
            HttpStatus.CREATED.value()
        ));
    }
    
    @GetMapping("/show-list")
    public String getMethodName(@RequestParam String param) {


        return new String();
    }
    
}
