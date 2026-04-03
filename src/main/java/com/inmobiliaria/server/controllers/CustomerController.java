package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.Customer.CustomerRequest;
import com.inmobiliaria.server.dto.Customer.CustomerResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.inmobiliaria.server.repositories.Customer.CustomerRepository;
import com.inmobiliaria.server.services.Customer.CustomerServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    Environment env;
    @Autowired
    CustomerServiceImpl customerServiceImpl;
    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/register")
    public ResponseEntity<CustomerResponse> postCustomer(@RequestBody @Valid CustomerRequest customer) throws CustomException {

        CustomerResponse customerRegistered = customerServiceImpl.registerCustomer(customer);

        return ResponseEntity.status(HttpStatus.OK).body(customerRegistered);
    }
    
    @GetMapping("/show-list")
    public ResponseEntity<List<CustomerResponse>> getCustomerList() throws CustomException {
        
        List<CustomerResponse> customerList = customerServiceImpl.getAllCustomers();
        
        return ResponseEntity.status(HttpStatus.OK).body(customerList);
    }

    /* 

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerResponse> putCustomer(@PathVariable Integer id, @RequestBody @Valid CustomerRequest customer) throws CustomException {

        CustomerResponse customerRegistered = customerServiceImpl.updateCustomer(id, customer);

        return ResponseEntity.status(HttpStatus.OK).body(customerRegistered);
    }*/
}
