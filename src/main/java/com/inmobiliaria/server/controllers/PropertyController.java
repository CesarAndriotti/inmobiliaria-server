package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.Property.PropertyRequest;
import com.inmobiliaria.server.dto.Property.PropertyResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.services.Property.PropertyServiceImpl;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    PropertyServiceImpl propertyServiceImpl;    

    @PostMapping("/register")
    public ResponseEntity<PropertyResponse> registerProperty(@RequestBody @Valid PropertyRequest propertyrequest) throws CustomException {
        
        PropertyResponse propertyResponse = propertyServiceImpl.registerProperty(propertyrequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyResponse);
    }

    @GetMapping("/show-list")
    public ResponseEntity<List<PropertyResponse>> getProperties() throws CustomException {

        List<PropertyResponse> propertyResponses = propertyServiceImpl.getAllProperties();
        return ResponseEntity.status(HttpStatus.OK).body(propertyResponses);
    }
}
