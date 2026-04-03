package com.inmobiliaria.server.dto.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    @NotBlank
    private String streetName;

    @NotBlank
    private String number;

    //FK
    @NotNull
    private Integer cityId;
}
