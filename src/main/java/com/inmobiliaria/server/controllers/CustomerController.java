package com.inmobiliaria.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.Customer;
import com.inmobiliaria.server.services.Customer.CustomerServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerServiceImpl;
    @Autowired
    Environment env;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerCustomer(@RequestBody Customer customer) throws CustomException{

        if (customer == null || customer.getAddress() == null) {
            throw new CustomException("", HttpStatus.BAD_REQUEST);
        }

        Customer registredCustomer = customerServiceImpl.registerCustomer(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(
            
            registredCustomer,
            "",
            HttpStatus.CREATED.value()
        ));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCustomer(@RequestBody Customer customer) throws CustomException {
        
        if (customer == null || customer.getAddress() == null) {
            throw new CustomException("", HttpStatus.BAD_REQUEST);
        }

        Customer updatedCustomer = customerServiceImpl.updateCustomer(customer);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseDto(
            updatedCustomer,
            null,
            HttpStatus.ACCEPTED.value()
        ));
    }
    
}
