package com.inmobiliaria.server.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.inmobiliaria.server.dto.State.StateRequest;
import com.inmobiliaria.server.dto.State.StateResponse;
import com.inmobiliaria.server.models.State;

@Mapper(componentModel = "spring")
public interface StateMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "country", ignore = true)
    State toEntity(StateRequest stateDto);

    @Mapping(source = "country.id", target = "countryId")
    StateResponse toDto(State state);

    List<State> toEntityList(List<StateRequest> stateDtos);

    List<StateResponse> toDtoList(List<State> states);
}
