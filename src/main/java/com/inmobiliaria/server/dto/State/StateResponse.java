package com.inmobiliaria.server.dto.State;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateResponse {

    @NotNull
    private Integer id;
    @NotBlank
    private String name;

    @NotNull
    private Integer countryId;
}
