package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.CustomerType;
import com.inmobiliaria.server.services.CustomerType.CustomerTypeServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/customer-types")
public class CustomerTypeController {
    
    @Autowired
    CustomerTypeServiceImpl customerTypeServiceImpl;
    @Autowired
    Environment env;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> postCustomerType(@RequestBody CustomerType customerType) throws CustomException {
        
        if (customerType == null) {
            throw new CustomException(env.getProperty("http.client.bad-request"), HttpStatus.BAD_REQUEST);
        }
        
        CustomerType registerdCustomerType = customerTypeServiceImpl.registerCustomerType(customerType);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto<>(

            registerdCustomerType,
            "",
            HttpStatus.CREATED.value()
        ));
    }
    
    @GetMapping("/show-list")
    public ResponseEntity<ResponseDto> showCustomerTypeList() throws CustomException {
        
        List<CustomerType> customerTypeList = customerTypeServiceImpl.showCustomerTypeList();
        
        
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(

            customerTypeList,
            "",
            HttpStatus.OK.value()
        ));
    }
    
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> putMethodName(@RequestBody CustomerType customerType) throws CustomException{

        if (customerType == null) {
            throw new CustomException(

                env.getProperty("http.client.bad-request"), 
                HttpStatus.BAD_REQUEST
            );
        }

        CustomerType updatedCustomerType = customerTypeServiceImpl.updateCustomerType(customerType);

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

        customerTypeServiceImpl.deleteCustomerType(id);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(

            env.getProperty("http.success.ok"),
            HttpStatus.OK.value()
        ));
    }
}
