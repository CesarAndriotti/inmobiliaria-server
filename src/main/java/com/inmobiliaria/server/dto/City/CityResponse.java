package com.inmobiliaria.server.dto.City;

import com.inmobiliaria.server.dto.State.StateResponse;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityResponse {

    @NotNull
    private int id;
    
    @NotBlank
    private String name;
 
    @NotNull
    private Integer stateId;
}
