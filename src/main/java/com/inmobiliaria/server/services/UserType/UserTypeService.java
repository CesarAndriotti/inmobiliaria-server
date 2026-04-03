package com.inmobiliaria.server.services.UserType;

import java.util.List;

import com.inmobiliaria.server.dto.UserType.UserTypeResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.UserType;

public interface UserTypeService {

    public List<UserTypeResponse> getAllUserTypes() throws CustomException;

    public UserTypeResponse registerUserType(UserTypeResponse userTypeDto) throws CustomException;

    public UserTypeResponse updateUserType(UserTypeResponse userType) throws CustomException;
}
