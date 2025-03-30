package com.inmobiliaria.server.services.UserType;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.inmobiliaria.server.dto.UserTypeDto;
import com.inmobiliaria.server.models.UserType;

@Mapper
public interface UserTypeMapper {

    UserTypeMapper INSTANCE = Mappers.getMapper(UserTypeMapper.class);
    UserType ToUserType(UserTypeDto userTypeDto);
    UserTypeDto ToUserTypeDto(UserType userType);
}
