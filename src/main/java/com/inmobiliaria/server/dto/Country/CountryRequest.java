package com.inmobiliaria.server.dto.Country;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String code;
}
