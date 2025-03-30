package com.inmobiliaria.server.dto;

import com.inmobiliaria.server.models.Address;
import com.inmobiliaria.server.models.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  
@NoArgsConstructor 
public class ResponseCustomerDto {

    private int id;
    private String name;
    private String lastname;
    private String identificationNumber;
    private java.sql.Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private CustomerTypeDto customerType;
    private Address address;

    public ResponseCustomerDto(Customer customer) {

        this.id = customer.getId();
        this.name = customer.getName();
        this.lastname = customer.getLastname();
        this.identificationNumber = customer.getIdentificationNumber();
        this.dateOfBirth = customer.getDateOfBirth();
        this.phoneNumber = customer.getPhoneNumber();
        this.email = customer.getEmail();

        this.address = customer.getAddress();  
        this.customerType = new CustomerTypeDto(customer.getCustomerType().getId(), customer.getCustomerType().getType());
    }
}
