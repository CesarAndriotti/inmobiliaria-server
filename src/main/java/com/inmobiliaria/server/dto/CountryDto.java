package com.inmobiliaria.server.dto;

import com.inmobiliaria.server.models.Country;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  
@NoArgsConstructor 
public class CountryDto {

    private Integer id;
    private String name;
    private String code;

    public CountryDto(Country country) {

        this.id = country.getId();
        this.name = country.getName();
        this.code = country.getCode();
    }
}
