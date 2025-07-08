package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.PropertyState;
import com.inmobiliaria.server.services.PropertyState.PropertyStateServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/property-states")
public class PropertyStateController {

    @Autowired
    Environment env;
    @Autowired
    PropertyStateServiceImpl propertyStateServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> postPropertyState(@RequestBody PropertyState propertystate) throws CustomException {
       
        if (propertystate == null) {
            
            throw new CustomException(
                env.getProperty("http.client.bad-request"),
                HttpStatus.BAD_REQUEST 
            );
        }

        PropertyState registeredPropertyState = propertyStateServiceImpl.registerPropertyState(propertystate);
        
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
            registeredPropertyState,
            env.getProperty("http.success.ok."),
            HttpStatus.OK.value()
        ));
    }
    
    @GetMapping("/show-list")
    public ResponseEntity<ResponseDto> getPropertyStateList() throws CustomException {

        List<PropertyState> propertyStateList = propertyStateServiceImpl.getAllPropertyStates();

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(

            propertyStateList,
            env.getProperty("http.success.ok"),
            HttpStatus.OK.value()
        ));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> putPropertyState(@RequestBody PropertyState propertyState) throws CustomException {
        
        if (propertyState == null) {
            
            throw new CustomException(

                env.getProperty("http.client.bad-request"), 
                HttpStatus.BAD_REQUEST
            );
        }
        
        PropertyState updatedPropertyState = propertyStateServiceImpl.updatePropertyState(propertyState);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
            
            updatedPropertyState,
            env.getProperty("http.success.accepted"),
            HttpStatus.ACCEPTED.value()
        ));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deletePropertyState(@PathVariable Integer id) throws CustomException {

        if(id == null || id == 0){

            throw new CustomException(
                env.getProperty("http.client.bad-request"),
                HttpStatus.BAD_REQUEST
            );
        }

        propertyStateServiceImpl.deletePropertyState(id);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(

            env.getProperty("http.success.ok"),
            HttpStatus.OK.value()
        ));
    }
}
