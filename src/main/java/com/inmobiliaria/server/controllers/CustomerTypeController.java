package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.CustomerType;
import com.inmobiliaria.server.services.Customer.CustomerServiceImpl;
import com.inmobiliaria.server.services.CustomerType.CustomerTypeServiceImpl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/customertypes")
public class CustomerTypeController {
    
    @Autowired
    CustomerTypeServiceImpl customerTypeServiceImpl;

    @PostMapping("/register")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        
        return entity;
    }
    
    @GetMapping("/show-list")
    public ResponseEntity<ResponseDto> showCustomerTypeList() throws CustomException {
        
        List<CustomerType> customerTypeList = customerTypeServiceImpl.showCustomerTypeList();
        
        
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(

            customerTypeList,
            "",
            HttpStatus.OK.value()
        ));
    }
    
}
