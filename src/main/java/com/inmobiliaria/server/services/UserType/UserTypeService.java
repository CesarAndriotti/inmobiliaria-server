package com.inmobiliaria.server.services.UserType;

import java.util.List;
import java.util.Optional;

import com.inmobiliaria.server.dto.UserTypeDto;
import com.inmobiliaria.server.models.UserType;

public interface UserTypeService{

    List<UserType> getAllUserTypes();
    Optional<UserType> registerUserType(UserTypeDto userTypeDto);
    Optional<UserType> getUserTypesById(Integer id);
    void deleteUserTypes(Integer id);
}
