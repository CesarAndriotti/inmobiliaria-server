package com.inmobiliaria.server.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.inmobiliaria.server.dto.Property.PropertyRequest;
import com.inmobiliaria.server.dto.Property.PropertyResponse;
import com.inmobiliaria.server.models.Property;

@Mapper(componentModel = "spring")
public interface PropertyMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "propertyState", ignore = true)
    @Mapping(target = "propertyType", ignore = true)
    @Mapping(target = "agent", ignore = true)
    @Mapping(target = "address", ignore = true)
    Property toEntity(PropertyRequest propertyRequest);
    
    @Mapping(source = "agent.id", target = "agentId")
    @Mapping(target = "addressId", source = "address.id")
    @Mapping(target = "propertyStateId", source = "propertyState.id")
    @Mapping(target = "propertyTypeId", source = "propertyType.id")
    PropertyResponse toDto(Property property);

    List<Property> toEntityList(List<PropertyRequest> propertyListResquest);
    List<PropertyResponse> toDtoList(List<Property> propertyList);
}
