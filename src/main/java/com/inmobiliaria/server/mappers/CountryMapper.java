package com.inmobiliaria.server.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.inmobiliaria.server.dto.Country.CountryRequest;
import com.inmobiliaria.server.dto.Country.CountryResponse;
import com.inmobiliaria.server.models.Country;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    @Mapping(target = "id", ignore = true)
    Country toEntity(CountryRequest countryDto);

    CountryResponse toDto(Country country);

    List<Country> toEntityList(List<CountryRequest> countryDtos);

    List<CountryResponse> toDtoList(List<Country> countries);
}
