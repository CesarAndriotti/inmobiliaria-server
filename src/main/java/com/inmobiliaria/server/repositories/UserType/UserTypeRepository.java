package com.inmobiliaria.server.repositories.UserType;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inmobiliaria.server.dto.UserTypeDto;
import com.inmobiliaria.server.models.UserType;

@Repository
public interface UserTypeRepository extends CrudRepository <UserType, Integer> {

    UserType save(UserTypeDto userTypeDto);
}
