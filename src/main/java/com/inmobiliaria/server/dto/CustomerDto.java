package com.inmobiliaria.server.dto;

import com.inmobiliaria.server.models.Address;
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
    private Address address;
}
