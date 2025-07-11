package com.inmobiliaria.server.services.UserType;

import java.util.List;

import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.UserType;

public interface UserTypeService{

    public List<UserType> getAllUserTypes() throws CustomException;
    public UserType registerUserType(UserType userType) throws CustomException;
    public UserType updateUserType(UserType userType) throws CustomException;
    public void deleteUserType(Integer id) throws CustomException;
}
