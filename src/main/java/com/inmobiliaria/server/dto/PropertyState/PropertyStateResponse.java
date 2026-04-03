package com.inmobiliaria.server.dto.PropertyState;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyStateResponse {

    @NotBlank
    private String name;
}

