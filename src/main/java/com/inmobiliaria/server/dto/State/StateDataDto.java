package com.inmobiliaria.server.dto.State;

import com.inmobiliaria.server.dto.Country.CountryDataDto;
import com.inmobiliaria.server.models.Country;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateDataDto {

    @NotNull
    private Integer id;
    @NotBlank
    private String name;
    @Valid
    private CountryDataDto country;
}
