package com.inmobiliaria.server.services.UserType;

import java.util.Optional;
import com.inmobiliaria.server.dto.UserTypeDto;

public interface UserTypeService{

    Optional<UserTypeDto> registerUserType(UserTypeDto userTypeDto);
}
