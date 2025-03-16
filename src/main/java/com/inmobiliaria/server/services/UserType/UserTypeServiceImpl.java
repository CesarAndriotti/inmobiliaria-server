package com.inmobiliaria.server.services.UserType;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inmobiliaria.server.dto.UserTypeDto;
import com.inmobiliaria.server.models.UserType;
import com.inmobiliaria.server.repositories.UserType.UserTypeRepository;

@Service
public class UserTypeServiceImpl implements UserTypeService{

    @Autowired
    UserTypeRepository userTypeRepository;

    @Override
    public List<UserType> getAllUserTypes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUserTypes'");
    }

    @Override
    public Optional<UserType> registerUserType(UserTypeDto userTypeDto) {

        UserType savedUserType = userTypeRepository.save(userTypeDto);
        return Optional.of(savedUserType);
    }

    @Override
    public Optional<UserType> getUserTypesById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserTypesById'");
    }

    @Override
    public void deleteUserTypes(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUserTypes'");
    }
}
