package com.inmobiliaria.server.services.UserType;

import java.util.List;

import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.UserType;

public interface UserTypeService{

    List<UserType> showUserTypeList() throws CustomException;
    UserType registerUserType(UserType userType) throws CustomException;
    UserType updateUserType(UserType userType) throws CustomException;
    Integer deleteUserType(Integer id) throws CustomException;
}
