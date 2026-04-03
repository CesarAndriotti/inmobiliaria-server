package com.inmobiliaria.server.services.User;

import java.util.List;

import com.inmobiliaria.server.dto.User.UserResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.User;

public interface UserService {

    //public User findUserNick(String nick);
    public List<UserResponse> getAllUsers() throws CustomException;
    public User saveUser(User user) throws CustomException;
}


