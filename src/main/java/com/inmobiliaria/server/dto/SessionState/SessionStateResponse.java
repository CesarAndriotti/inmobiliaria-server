package com.inmobiliaria.server.dto.SessionState;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionStateResponse {
    
    @NotNull
    private Integer id;

    @NotBlank
    private String name;
}
