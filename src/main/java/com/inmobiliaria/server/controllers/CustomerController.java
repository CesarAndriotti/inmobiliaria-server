package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.exceptions.CustomException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.inmobiliaria.server.models.Customer;
import com.inmobiliaria.server.repositories.Customer.CustomerRepository;
import com.inmobiliaria.server.services.Customer.CustomerServiceImpl;

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
    public ResponseEntity<ResponseDto> postCustomer(@RequestBody Customer customer) throws CustomException {

        if(customer == null || customer.getAddress() == null || customer.getCustomerType() == null) 
        { 
            throw new CustomException(
                env.getProperty("http.client.bad-request"), 
                HttpStatus.BAD_REQUEST
            );
        }

        Customer customerRegistered = customerServiceImpl.registerCustomer(customer);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
            
            customerRegistered,
            env.getProperty("http.success.created"),
            HttpStatus.CREATED.value()
        ));
    }
    
    @GetMapping("/show-list")
    public ResponseEntity<ResponseDto> getCustomerList() throws CustomException {
        
        List<Customer> customerList = customerServiceImpl.getAllCustomers();
        
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
            customerList,
            env.getProperty("http.success.ok"),
            HttpStatus.OK.value()
        ));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> putCustomer(@RequestBody Customer customer) throws CustomException {

        if(customer == null || customer.getAddress() == null || customer.getCustomerType() == null) 
        { 
            throw new CustomException(
                env.getProperty("http.client.bad-request"), 
                HttpStatus.BAD_REQUEST
            );
        }

        Customer customerRegistered = customerServiceImpl.updateCustomer(customer);
    
        if (customerRegistered == null) throw new CustomException(
            env.getProperty("database.update-failed"), 
            HttpStatus.CONFLICT
        );

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
            
            customerRegistered,
            env.getProperty("http.success.created"),
            HttpStatus.CREATED.value()
        ));
    }
}
