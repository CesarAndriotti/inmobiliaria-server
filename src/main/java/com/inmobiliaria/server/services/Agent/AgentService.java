package com.inmobiliaria.server.services.Agent;

import java.util.List;
import java.util.Optional;
import com.inmobiliaria.server.models.Agent;
import com.inmobiliaria.server.dto.UserDto;

public interface AgentService {

    public Optional<UserDto> registerAgentAndUser(UserDto userDto);
}
