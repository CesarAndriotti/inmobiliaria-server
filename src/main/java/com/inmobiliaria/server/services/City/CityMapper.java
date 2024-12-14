package com.inmobiliaria.server.services.City;
import org.mapstruct.factory.Mappers;

import com.inmobiliaria.server.dto.CityDto;
import com.inmobiliaria.server.models.City;

public interface CityMapper {

    CityMapper mapper = Mappers.getMapper(CityMapper.class);

    CityDto CityToCityDto(City City);
    City CityDtoToCity(CityDto CityDto);
}

