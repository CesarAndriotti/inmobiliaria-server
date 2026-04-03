package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.CustomerType.CustomerTypeResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.services.CustomerType.CustomerTypeServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

//Actualizado

@RestController
@RequestMapping("/api/customer-types")
public class CustomerTypeController {
    
    @Autowired
    CustomerTypeServiceImpl customerTypeServiceImpl;
    @Autowired
    Environment env;

    @GetMapping("/show-list")
    public ResponseEntity<List<CustomerTypeResponse>> getCustomerTypeList() throws CustomException {

        List<CustomerTypeResponse> customerTypeList = customerTypeServiceImpl.getAllCustomerTypes();

        return ResponseEntity.status(HttpStatus.OK).body(customerTypeList);
    }
}
