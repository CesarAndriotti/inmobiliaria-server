package com.inmobiliaria.server.services.User;

import java.util.List;
import com.inmobiliaria.server.models.User;
import com.inmobiliaria.server.dto.UserDto;

public interface UserService {

    public UserDto registerUser(UserDto userDto);
}


