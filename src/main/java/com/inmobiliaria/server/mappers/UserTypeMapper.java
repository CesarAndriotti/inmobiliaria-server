package com.inmobiliaria.server.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.inmobiliaria.server.dto.UserType.UserTypeRequest;
import com.inmobiliaria.server.dto.UserType.UserTypeResponse;
import com.inmobiliaria.server.models.UserType;

@Mapper(componentModel = "spring")
public interface UserTypeMapper {

    @Mapping(target = "id", ignore = true)
    UserType toEntity(UserTypeRequest userTypeDto);

    UserTypeResponse toDto(UserType userType);

    List<UserType> toEntityUserTypeList(List<UserTypeRequest> userTypeDtos);

    List<UserTypeResponse> toUserTypeDtoList(List<UserType> userTypes);
}
