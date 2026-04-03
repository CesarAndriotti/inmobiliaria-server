package com.inmobiliaria.server.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.inmobiliaria.server.dto.Session.SessionRequest;
import com.inmobiliaria.server.dto.Session.SessionResponse;
import com.inmobiliaria.server.models.Session;
import com.inmobiliaria.server.models.SessionState;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "sessionState", ignore = true) //Los debe ignorar porque son objetos y solo tiene id
    Session toEntity(SessionRequest sessionRequest);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "sessionState.id", target = "sessionStateId")
    SessionResponse toDto(Session session);

    List<Session> toEntityList(List<SessionRequest> sessionDtos);

    List<SessionResponse> toDtoList(List<Session> sessions);
}
