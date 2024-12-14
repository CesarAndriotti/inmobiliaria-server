package com.inmobiliaria.server.dto;

import com.inmobiliaria.server.models.Address;
import com.inmobiliaria.server.models.City;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  
@NoArgsConstructor 
public class AddressDto {

    private int id;
    private String streetName;
    private String number;
    private City city;

    public AddressDto(Address address) {

        this.id = address.getId();
        this.streetName = address.getStreetName();
        this.number = address.getNumber();
        this.city = new City();
    }
}
