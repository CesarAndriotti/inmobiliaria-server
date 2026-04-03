package com.inmobiliaria.server.dto.City;

import com.inmobiliaria.server.dto.State.StateDataDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDataDto {

    @NotNull
    private Integer id;
    @NotBlank
    private String name;
    @Valid
    private StateDataDto state;
    
}
