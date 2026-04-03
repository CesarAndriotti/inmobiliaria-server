package com.inmobiliaria.server.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.inmobiliaria.server.dto.AgentState.AgentStateRequest;
import com.inmobiliaria.server.dto.AgentState.AgentStateResponse;
import com.inmobiliaria.server.models.AgentState;

@Mapper(componentModel = "spring")
public interface AgentStateMapper {

    @Mapping(target = "id", ignore = true)
    AgentState toEntity(AgentStateRequest request);

    AgentStateResponse toDto(AgentState agentState);

    List<AgentState> toEntityList(List<AgentStateRequest> requests);

    List<AgentStateResponse> toDtoList(List<AgentState> agentStates);

}
