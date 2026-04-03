package com.inmobiliaria.server.dto.AgentState;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentStateRequest {

    @NotBlank
    private String name;
}
