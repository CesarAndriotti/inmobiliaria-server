package com.inmobiliaria.server.services.Agent;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.inmobiliaria.server.dto.AgentDto;
import com.inmobiliaria.server.dto.ResponseAgentDto;
import com.inmobiliaria.server.models.Agent;

@Mapper
public interface AgentMapper {

    AgentMapper INSTANCE = Mappers.getMapper(AgentMapper.class);

    Agent ToAgent(AgentDto AgentDto);
    AgentDto ToAgentDto(Agent Agent);
    ResponseAgentDto AgentToResponseAgentDto(Agent Agent);
}