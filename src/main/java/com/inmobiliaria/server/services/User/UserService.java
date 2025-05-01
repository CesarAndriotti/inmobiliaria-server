package com.inmobiliaria.server.services.User;

import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.User;

public interface UserService {

    //public User findUserNick(String nick);
    public User registerUserAndAgent(User user) throws CustomException;
}


