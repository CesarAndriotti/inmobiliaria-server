package com.inmobiliaria.server.services.User;

import java.util.Optional;

import com.inmobiliaria.server.models.User;

public interface UserService {

    public User registerUser(User user);
    public User findUserNick(String nick);
}


