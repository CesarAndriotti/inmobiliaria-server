package com.inmobiliaria.server.dto.Agent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentResponse {

    @NotNull
    private int id;

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
    @NotBlank
    private String agentRegistration;
    @NotBlank
    private String profilePhoto;

    @NotNull
    private Integer agentStateId;

    @NotNull
    private Integer addressId;  
}
