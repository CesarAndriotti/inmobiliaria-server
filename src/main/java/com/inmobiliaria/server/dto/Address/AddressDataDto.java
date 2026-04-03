package com.inmobiliaria.server.dto.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDataDto {

    @NotNull
    private Integer id;

    @NotBlank
    private String streetName;

    @NotBlank
    private String number;

    @NotNull
    private Integer cityId;
}
