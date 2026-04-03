package com.inmobiliaria.server.dto.PropertyState;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyStateRequest{

    @NotNull
    private Integer id;

    @NotBlank
    private String name;
}
