package com.inmobiliaria.server.dto.CustomerType;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTypeRequest {

    @NotBlank
    private String name;
}
