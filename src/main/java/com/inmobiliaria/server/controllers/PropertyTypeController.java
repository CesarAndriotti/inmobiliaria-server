package com.inmobiliaria.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.PropertyType;
import com.inmobiliaria.server.services.PropertyType.PropertyTypeServiceImpl;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


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
    public ResponseEntity<ResponseDto> getPropertyTypeList() throws CustomException {
        
        List<PropertyType> propertyTypeList = propertyTypeServiceImpl.getAllPropertyTypes();

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
            propertyTypeList,
            env.getProperty("http.success.ok"),
            HttpStatus.OK.value()
        ));
    }
    
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> putPropertyType(@RequestBody PropertyType propertyType) throws CustomException {
        
        if (propertyType == null ){
            
            throw new CustomException(
                env.getProperty("http.client.bad-request"),
                HttpStatus.BAD_REQUEST 
            );
        }

        PropertyType updatedPropertyType = propertyTypeServiceImpl.updatePropertyType(propertyType);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(

            updatedPropertyType,
            env.getProperty("http.success.accepted"),
            HttpStatus.ACCEPTED.value()
        ));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deletePropertyType(@PathVariable Integer id) throws CustomException {

        if (id == null || id == 0) {
            
            throw new CustomException(
                env.getProperty("http.client.bad-request"),
                HttpStatus.BAD_REQUEST
            );
        }

        propertyTypeServiceImpl.deletePropertyType(id);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(

            env.getProperty("http.success.ok"),
            HttpStatus.OK.value()
        ));
    }
}
