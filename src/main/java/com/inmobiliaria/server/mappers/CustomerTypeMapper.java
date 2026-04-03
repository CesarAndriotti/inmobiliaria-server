package com.inmobiliaria.server.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.inmobiliaria.server.dto.CustomerType.CustomerTypeRequest;
import com.inmobiliaria.server.dto.CustomerType.CustomerTypeResponse;
import com.inmobiliaria.server.models.CustomerType;

@Mapper(componentModel = "spring")
public interface CustomerTypeMapper {

    @Mapping(target = "id", ignore = true)
    CustomerType toEntity(CustomerTypeRequest customerTypeDto);

    CustomerTypeResponse toDto(CustomerType customerType);

    List<CustomerType> toEntityList(List<CustomerTypeRequest> customerTypeRequests);

    List<CustomerTypeResponse> toDtoList(List<CustomerType> customerTypes);
}
