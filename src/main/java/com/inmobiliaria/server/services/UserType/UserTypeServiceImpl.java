package com.inmobiliaria.server.services.UserType;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.models.UserType;
import com.inmobiliaria.server.repositories.UserType.UserTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.QueryTimeoutException;

@Service
public class UserTypeServiceImpl implements UserTypeService{

    @Autowired
    UserTypeRepository userTypeRepository;

    @Override
    public List<UserType> showUserTypeList() {
        
        try {
            List<UserType> userTypeListed = userTypeRepository.findAll();

            if (userTypeListed.isEmpty()) {
                throw new EntityNotFoundException("");
            }

            return userTypeListed;

        } catch (QueryTimeoutException e) {
            throw new QueryTimeoutException("");
        } 
    }
    
    @Override
    public UserType registerUserType(UserType userType) {
        
        UserType newUserType = userTypeRepository.save(userType);

        return newUserType;
    }

    @Override
    public UserType updateUserType(UserType userType) {
        
        try {
            UserType userTypeUpdated = userTypeRepository.save(userType); 
            return userTypeUpdated;
         
        } catch (EntityNotFoundException e) {
            
            throw e;
        }
    }

    @Override
    public Integer deleteUserType(Integer id){
         
        try {
            userTypeRepository.deleteById(id);
            return id;
        
        } catch (Exception e) {
            throw new RuntimeException("", e);
        }
    }
}
