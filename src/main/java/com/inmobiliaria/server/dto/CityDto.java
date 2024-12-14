package com.inmobiliaria.server.dto;

import com.inmobiliaria.server.models.City;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  
@NoArgsConstructor 
public class CityDto {

    private int id;
    private String name;
    private StateDto state;

    public CityDto(City city) {

        this.id = city.getId();
        this.name = city.getName();
        this.state = new StateDto(city.getState());
    } 
}
