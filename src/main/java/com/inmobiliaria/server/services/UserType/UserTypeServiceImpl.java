package com.inmobiliaria.server.services.UserType;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.UserType;
import com.inmobiliaria.server.repositories.UserType.UserTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.QueryTimeoutException;

@Service
public class UserTypeServiceImpl implements UserTypeService{

    @Autowired
    UserTypeRepository userTypeRepository;
    @Autowired
    Environment env;

    @Override
    public List<UserType> showUserTypeList() throws CustomException {
        
        try {
            List<UserType> userTypeListed = userTypeRepository.findAll();

            if (userTypeListed.isEmpty()) {
                throw new CustomException(env.getProperty("database.entity-not-found"), HttpStatus.NOT_FOUND);
            }

            return userTypeListed;

        } catch (QueryTimeoutException e) {
            throw new CustomException(env.getProperty("database.query-timeout"), HttpStatus.NOT_FOUND);
        } 
    }
    
    @Override
    public UserType registerUserType(UserType userType) throws CustomException {
        
        try{
            UserType newUserType = userTypeRepository.save(userType);
            return newUserType;
        }
        catch (DataAccessException ex) {
            throw new CustomException("database.delete-failed", HttpStatus.CONFLICT);
        }
    }

    @Override
    public UserType updateUserType(UserType userType) throws CustomException {
        
        try {
            UserType updatedUserType = userTypeRepository.save(userType); 
            return updatedUserType;
         
        } catch (EntityNotFoundException e) {
            
            throw new CustomException(env.getProperty("database.entity-not-found"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Integer deleteUserType(Integer id) throws CustomException{
         
        try {
            userTypeRepository.deleteById(id);
            return id;
        
        } catch (Exception e) {
            throw new CustomException(env.getProperty("database.query-timeout"), HttpStatus.REQUEST_TIMEOUT);
        }
    }
}
