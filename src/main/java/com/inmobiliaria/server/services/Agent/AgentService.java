package com.inmobiliaria.server.services.Agent;

import java.util.Optional;
import com.inmobiliaria.server.dto.AgentDto;
import com.inmobiliaria.server.dto.UserDto;

public interface AgentService {

    public Optional<UserDto> registerAgentAndUser(UserDto userDto);
    public Optional<AgentDto> updateAgentData(AgentDto agentDto);
}
