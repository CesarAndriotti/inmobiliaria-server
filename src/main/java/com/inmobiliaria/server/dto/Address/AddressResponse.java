package com.inmobiliaria.server.dto.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    @NotNull
    private Integer id;

    @NotBlank
    private String streetName;

    @NotBlank
    private String number;

    // FK

    @NotNull
    private Integer addressId;

    @NotBlank
    private String addressStreetName;

    @NotBlank
    private String addressNumber;

    @NotNull
    private Integer cityId;
}
