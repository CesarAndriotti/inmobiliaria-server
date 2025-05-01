package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.CustomerType;
import com.inmobiliaria.server.services.CustomerType.CustomerTypeServiceImpl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/customertypes")
public class CustomerTypeController {

    @Autowired
    CustomerTypeServiceImpl customerTypeServiceImpl;

    @PostMapping("/register")
    public String registerCustomerType(@RequestBody String entity) {
        
        
        
        return entity;
    }

    @GetMapping("/show-list")
    public ResponseEntity<ResponseDto> getCustomerTypes() throws CustomException {

        List<CustomerType> customerTypeslist = customerTypeServiceImpl.showCustomerTypeList();
        
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(
            customerTypeslist,
            "",
            HttpStatus.OK.value()
        ));
    }
    
    @PutMapping("/update/{id}")
    public String updateCustomerType(@PathVariable String id, @RequestBody String entity) {
        //TODO: process PUT request
        
        return entity;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomerType(@PathVariable String id) {
        return id.toString();
    }
}
