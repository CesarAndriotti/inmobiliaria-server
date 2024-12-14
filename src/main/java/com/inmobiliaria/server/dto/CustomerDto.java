package com.inmobiliaria.server.dto;

import java.sql.Date;

import com.inmobiliaria.server.models.Address;
import com.inmobiliaria.server.models.CustomerType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  
@NoArgsConstructor 
public class CustomerDto {

    private int id;
    private String name;
    private String lastname;
    private String identificationNumber;
    private java.sql.Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private CustomerTypeDto customerType;
    private AddressDto address;
}
