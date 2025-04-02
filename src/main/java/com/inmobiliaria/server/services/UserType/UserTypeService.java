package com.inmobiliaria.server.services.UserType;

import java.util.List;
import com.inmobiliaria.server.models.UserType;

public interface UserTypeService{

    List<UserType> showUserTypeList();
    UserType registerUserType(UserType userType);
    UserType updateUserType(UserType userType);
}
