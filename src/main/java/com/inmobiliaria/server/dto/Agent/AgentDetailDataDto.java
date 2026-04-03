package com.inmobiliaria.server.dto.Agent;

import com.inmobiliaria.server.dto.Address.AddressDataDto;
import com.inmobiliaria.server.dto.AgentState.AgentStateDataDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentDetailDataDto {

    Integer id;
    String name;
    String lastname;
    String identificationNumber;
    java.sql.Date dateOfBirth;
    String phoneNumber;
    String email;
    String agentRegistration;
    String profilePhoto;
    AgentStateDataDto agentState;
    AddressDataDto address;
}
