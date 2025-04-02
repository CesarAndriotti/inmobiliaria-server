package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.inmobiliaria.server.MessageConstants;
import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.models.User;
import com.inmobiliaria.server.models.UserType;
import com.inmobiliaria.server.services.UserType.UserTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/usertypes")
public class UserTypeController {

    @Autowired
    UserTypeServiceImpl userTypeServiceImpl;

    @GetMapping("/show-list")
    public ResponseEntity<ResponseDto> getUserTypeList() {

        List<UserType> userTypeListed = userTypeServiceImpl.showUserTypeList();

        if (userTypeListed.isEmpty() || userTypeListed == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto<>(
                userTypeListed,
                MessageConstants.ERROR_NOT_FOUND,
                null,
                HttpStatus.NOT_FOUND.value(),
                new Date()
            ));
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
                userTypeListed,
                "",
                null,
                HttpStatus.OK.value(),
                new Date()
            ));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> UserTypeRegister(@RequestBody UserType userType) {
        
        if(userType == null || userType.getType() == null){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(
                null,
                MessageConstants.ERROR_BAD_REQUEST,
                null, 
                HttpStatus.BAD_REQUEST.value(), 
                new Date()
            ));
        }

        UserType userTypeRegistered = userTypeServiceImpl.registerUserType(userType);

        if(userTypeRegistered == null){
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(
                null,
                MessageConstants.ERROR_CREATE_FAILED,
                null,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date()
            ));
        }
        else{
            
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(
                null,
                MessageConstants.SUCCESS_CREATE,
                null,
                HttpStatus.CREATED.value(),
                new Date()
            ));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateUserType(@RequestBody UserType userType) {
        
        if (userType == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(

                null,
                MessageConstants.ERROR_BAD_REQUEST,
                null,
                HttpStatus.BAD_REQUEST.value(),
                new Date()
            ));
        }

        UserType userTypeUpdated = userTypeServiceImpl.updateUserType(userType);

        if (userTypeUpdated == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(

                null,
                "",
                null,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date()
            ));
        }
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseDto(
            
        null,
        "Success: The userType has been updated successfully",
            null,
            HttpStatus.ACCEPTED.value(),
            new Date()
        ));
    }

    public ResponseEntity<User> deleteUserType(@RequestBody UserType userType){

        Integer id = userType.getId();
        userTypeServiceImpl.deleteUserType(id);

        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
}
