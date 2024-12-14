package com.inmobiliaria.server.services.AgentState;

import org.mapstruct.factory.Mappers;
import com.inmobiliaria.server.dto.AgentStateDto;
import com.inmobiliaria.server.models.AgentState;

public interface AgentStateMapper {

    AgentStateMapper INSTANCE = Mappers.getMapper(AgentStateMapper.class);

    AgentState ToAgentState(AgentStateDto agentStateDto);
    AgentStateDto ToAgentStateDto(AgentState agentState);
}
