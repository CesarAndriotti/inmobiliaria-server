package com.inmobiliaria.server.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.inmobiliaria.server.dto.User.UserRequest;
import com.inmobiliaria.server.dto.User.UserResponse;
import com.inmobiliaria.server.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "agent", ignore = true)
    @Mapping(target = "userType", ignore = true)
    User toEntity(UserRequest userDto);

    @Mapping(source = "agent.id", target = "agentId")
    @Mapping(source = "userType.id", target = "userTypeId")
    UserResponse toDto(User user);

    List<User> toEntityList(List<UserRequest> userDtos);

    List<UserResponse> toDtoList(List<User> users);  
}
