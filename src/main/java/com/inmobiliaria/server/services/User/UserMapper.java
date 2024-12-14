package com.inmobiliaria.server.services.User;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.inmobiliaria.server.dto.UserDto;
import com.inmobiliaria.server.models.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User ToUser(UserDto userDto);
    UserDto ToUserDto(User user);
}
