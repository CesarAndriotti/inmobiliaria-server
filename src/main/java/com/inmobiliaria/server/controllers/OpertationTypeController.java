package com.inmobiliaria.server.controllers;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/operation-types")
public class OpertationTypeController {

    @Autowired
    OpertationTypeServiceImpl opertationTypeServiceImpl;
    @Autowired
    Environment env;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> postCustomerType(@RequestBody OperationType operationType) throws CustomException {
        
        if (operationType == null) {
            throw new CustomException(
                env.getProperty("http.client.bad-request"), 
                HttpStatus.BAD_REQUEST
            );
        }
        
        OperationType registerdOperationType = opertationTypeServiceImpl.registerOperationType(operationType);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto<>(

            registerdOperationType,
            env.getProperty("http.success.ok"),
            HttpStatus.CREATED.value()
        ));
    }
    
    @GetMapping("/show-list")
    public ResponseEntity<ResponseDto> showCustomerTypeList() throws CustomException {
        
        List<OperationType> customerTypeList = opertationTypeServiceImpl.getAllOpertationTypes();
        
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(

            customerTypeList,
            env.getProperty("http.success.ok"),
            HttpStatus.OK.value()
        ));
    }
    
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> putMethodName(@RequestBody OperationType operationType) throws CustomException{

        if (operationType == null) {
            throw new CustomException(

                env.getProperty("http.client.bad-request"), 
                HttpStatus.BAD_REQUEST
            );
        }

        OperationType updatedCustomerType = opertationTypeServiceImpl.updateOperationType(operationType);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(

            updatedCustomerType,
            env.getProperty("http.success.accepted"),
            HttpStatus.ACCEPTED.value()
        ));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteCustomerType(@PathVariable Integer id) throws CustomException {

        if (id == null || id == 0) {
            
            throw new CustomException(
                env.getProperty("http.client.bad-request"),
                HttpStatus.BAD_REQUEST
            );
        }

        opertationTypeServiceImpl.deleteOperationType(id);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(

            env.getProperty("http.success.ok"),
            HttpStatus.OK.value()
        ));
    }
}
