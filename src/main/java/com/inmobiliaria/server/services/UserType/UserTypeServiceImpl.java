package com.inmobiliaria.server.services.UserType;

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
    public Optional<UserTypeDto> registerUserType(UserTypeDto userTypeDto) {

        UserType userTypeToRegister = new UserType(userTypeDto.getId(), userTypeDto.getType());
        
        try {

            UserType newUserType = userTypeRepository.save(userTypeToRegister);
            UserTypeDto newUserTypeDto = new UserTypeDto(newUserType.getId(), newUserType.getType());
            return Optional.of(newUserTypeDto);

        } catch (Exception e) {
            
            return Optional.empty();
        }
    }
}
