package com.inmobiliaria.server.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.inmobiliaria.server.dto.SessionState.SessionStateRequest;
import com.inmobiliaria.server.dto.SessionState.SessionStateResponse;
import com.inmobiliaria.server.models.SessionState;

@Mapper(componentModel = "spring")
public interface SessionStateMapper {

    @Mapping(target = "id", ignore = true)
    SessionState toEntity(SessionStateRequest sessionStateRequest);

    SessionStateResponse toDto(SessionState sessionState);

    List<SessionState> toEntityList(List<SessionStateRequest> sessionStateRequests);

    List<SessionStateResponse> toDtoList(List<SessionState> sessionStates);
}
