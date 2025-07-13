package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.Property;
import com.inmobiliaria.server.services.Property.PropertyServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    PropertyServiceImpl propertyServiceImpl;
    @Autowired
    Environment env;

    @GetMapping("/show-list")
    public ResponseEntity<ResponseDto> getPropertyList() throws CustomException {
        
        List<Property> propertyList = propertyServiceImpl.getAllProperties();
        
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
            propertyList,
            env.getProperty("http.success.ok"),
            HttpStatus.CREATED.value()

        ));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> postMethodName(@RequestBody Property property) throws CustomException {
        

        if (property == null) {
            
            throw new CustomException(
                env.getProperty("http.client.bad-request"), 
                HttpStatus.BAD_REQUEST
            );
        }

        Property propertyRegistered = propertyServiceImpl.registerProperty(property);


        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
            propertyRegistered,
            env.getProperty("http.success.created"),
            HttpStatus.CREATED.value()

        ));
    }
    
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> putMethodName(@PathVariable String id, @RequestBody String entity) {
        //TODO: process PUT request
        
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteProperty(){
        return null;
    }
}
