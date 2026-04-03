package com.inmobiliaria.server.dto.PropertyType;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyTypeResponse {
    
    @NotBlank
    private String name;
}
