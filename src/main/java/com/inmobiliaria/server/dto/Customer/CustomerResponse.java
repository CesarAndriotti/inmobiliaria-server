package com.inmobiliaria.server.dto.Customer;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    @NotNull
    private Integer id;

    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
    @NotBlank
    private String identificationNumber;
    @NotNull
    private java.sql.Date dateOfBirth;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String email;

    @NotNull
    private Integer addressId;

    @NotBlank
    private String addressStreetName;

    @NotBlank
    private String addressNumber;

    @NotNull
    private Integer cityId;

    @NotNull
    private Integer customerTypeId;
}
