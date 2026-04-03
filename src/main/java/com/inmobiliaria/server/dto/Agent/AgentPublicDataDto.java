package com.inmobiliaria.server.dto.Agent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentPublicDataDto {

    String name;
    String lastname;
    String agentRegistration;

}
