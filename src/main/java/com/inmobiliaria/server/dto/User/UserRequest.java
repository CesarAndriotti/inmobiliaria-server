package com.inmobiliaria.server.dto.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank
    private String nick;

    @NotBlank
    private String password;

    @NotNull
    private Integer agentId;

    @NotNull
    private Integer userTypeId;
}
