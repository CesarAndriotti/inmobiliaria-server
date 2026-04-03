package com.inmobiliaria.server.services.UserType;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.inmobiliaria.server.dto.UserType.UserTypeResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.mappers.UserMapper;
import com.inmobiliaria.server.mappers.UserTypeMapper;
import com.inmobiliaria.server.models.UserType;
import com.inmobiliaria.server.repositories.UserType.UserTypeRepository;

@Service
public class UserTypeServiceImpl implements UserTypeService {

    @Autowired
    UserTypeRepository userTypeRepository;
    @Autowired
    Environment env;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserTypeMapper userTypeMapper;

    @Override
    public List<UserTypeResponse> getAllUserTypes() throws CustomException {

        try {
            List<UserType> userTypeList = userTypeRepository.findAll();
            List<UserTypeResponse> userTypeDtos = userTypeMapper.toUserTypeDtoList(userTypeList);

            return userTypeDtos;
        } catch (DataAccessException e) {

            throw new CustomException(
                    env.getProperty("data.access-error") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new CustomException(
                    env.getProperty("unhandled-exception") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UserTypeResponse registerUserType(UserTypeResponse userTypeResponse) throws CustomException {

        try {
            Optional<UserType> userTypeDatabase = userTypeRepository.findByName(userTypeResponse.getName());

            if (userTypeDatabase.isPresent()) {
                throw new CustomException(
                        env.getProperty("database.existing-data"),
                        HttpStatus.CONFLICT);
            }

            UserType userType = new UserType();
            userType.setName(userTypeResponse.getName());
            UserType newUserType = userTypeRepository.save(userType);

            UserTypeResponse newUserTypeDto = new UserTypeResponse();
            newUserTypeDto.setId(newUserType.getId());
            return newUserTypeDto;
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(
                    env.getProperty("database.data-integrity-violation") + ": " + e.getMessage(),
                    HttpStatus.CONFLICT);
        } catch (DataAccessException e) {
            throw new CustomException(
                    env.getProperty("data.access-error") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new CustomException(
                    env.getProperty("unhadled-error") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UserTypeResponse updateUserType(UserTypeResponse userTypeResponse) throws CustomException {

        try {

            Optional<UserType> userTypeDatabase = userTypeRepository.findById(userTypeResponse.getId());

            if (userTypeDatabase.isPresent()) {

                throw new CustomException(
                        env.getProperty("database.existing-data"),
                        HttpStatus.CONFLICT);
            }

            UserType userType = new UserType();
            userType.setId(userTypeResponse.getId());
            userType.setName(userTypeResponse.getName());
            UserType updatedUserType = userTypeRepository.save(userType);
            UserTypeResponse updatedUserTypeDto = userTypeMapper.toDto(updatedUserType);
            return updatedUserTypeDto;

        } catch (DataIntegrityViolationException e) {
            throw new CustomException(
                    env.getProperty("database.data-integrity-violation") + ": " + e.getMessage(),
                    HttpStatus.CONFLICT);
        } catch (DataAccessException e) {
            throw new CustomException(
                    env.getProperty("data.access-error") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new CustomException(
                    env.getProperty("unhadled-error") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
