package com.inmobiliaria.server.dto.SessionState;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionStateRequest {
    
    @NotBlank
    private String name;
}
