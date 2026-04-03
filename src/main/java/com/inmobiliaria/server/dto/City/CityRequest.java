package com.inmobiliaria.server.dto.City;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityRequest {

    @NotBlank
    private String name;

    @NotNull
    private Integer stateId;
}
