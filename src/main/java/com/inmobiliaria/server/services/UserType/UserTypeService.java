package com.inmobiliaria.server.services.UserType;

import java.util.List;
import java.util.Optional;
import com.inmobiliaria.server.models.UserType;

public interface UserTypeService {

    List<UserType> getAllStates();
    Optional<UserType> saveState(UserType State);
    Optional<UserType> getStateById(Integer id);
    void deleteState(Integer id);
}
