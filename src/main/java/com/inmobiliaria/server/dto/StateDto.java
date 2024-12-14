package com.inmobiliaria.server.dto;

import com.inmobiliaria.server.models.State;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  
@NoArgsConstructor 
public class StateDto {

    private Integer id;
    private String name; 
    private CountryDto country;

    public StateDto(State state) {
        
        this.id = state.getId();
        this.name = state.getName();
        this.country = new CountryDto(state.getCountry());
    }
}
