package com.inmobiliaria.server.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.inmobiliaria.server.dto.Agent.AgentRequest;
import com.inmobiliaria.server.dto.Agent.AgentResponse;
import com.inmobiliaria.server.models.Agent;

@Mapper(componentModel = "spring")
public interface AgentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "agentState", ignore = true)
    @Mapping(target = "address", ignore = true)
    Agent toEntity(AgentRequest agentRequest);

    @Mapping(target = "agentStateId", source = "agentState.id")
    @Mapping(target = "addressId", source = "address.id")
    AgentResponse toDto(Agent agent);

    

    List<Agent> toEntityList(List<AgentRequest> agentRequests);

    List<AgentResponse> toDtoList(List<Agent> agents);
}
