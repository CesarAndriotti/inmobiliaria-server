package com.inmobiliaria.server.services.UserType;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.models.UserType;
import com.inmobiliaria.server.repositories.UserType.UserTypeRepository;

@Service
public class UserTypeServiceImpl implements UserTypeService{

    @Autowired
    UserTypeRepository userTypeRepository;

    public List<UserType> showUserTypeList() {
        
        try {
            List<UserType> userTypeListed = userTypeRepository.findAll();
            return userTypeListed;

        } catch (Exception e) {
            
            return null;
        } 
    }
    
    @Override
    public UserType registerUserType(UserType userType) {
        
        try {

            UserType newUserType = userTypeRepository.save(userType);
            return newUserType;

        } catch (Exception e) {
            
            return null;
        }
    }

    @Override
    public UserType updateUserType(UserType userType) {
        
        UserType userTypeUpdated = userTypeRepository.save(userType); 
        return userTypeUpdated;
    }

    public Optional<UserType> deleteUserType(Integer id) {
         

        return null;
    }

    
}
