package com.inmobiliaria.server.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.inmobiliaria.server.dto.City.CityRequest;
import com.inmobiliaria.server.dto.City.CityResponse;
import com.inmobiliaria.server.models.City;

@Mapper(componentModel = "spring")
public interface CityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "state", ignore = true)
    City toEntity(CityRequest cityRequest);

    @Mapping(source = "state.id", target = "stateId")
    CityResponse toDto(City city);

    List<City> toEntityList(List<CityRequest> cityRequestList);

    List<CityResponse> toDtoList(List<City> cityList);
}
