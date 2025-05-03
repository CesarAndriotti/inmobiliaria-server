package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.UserType;
import com.inmobiliaria.server.services.UserType.UserTypeServiceImpl;
import jakarta.persistence.QueryTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/usertypes")
public class UserTypeController {

    @Autowired
    UserTypeServiceImpl userTypeServiceImpl;
    @Autowired
    Environment env;

    @GetMapping("/show-list")
    public ResponseEntity<ResponseDto> getUserTypeList() throws CustomException {

        try {
            List<UserType> userTypeListed = userTypeServiceImpl.showUserTypeList();

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
                userTypeListed,
                env.getProperty("http.success.ok"),
                HttpStatus.OK.value()
            ));
        }
        catch (QueryTimeoutException e) {
            
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new ResponseDto(
                env.getProperty("database.query-timeout"),
                HttpStatus.GATEWAY_TIMEOUT.value()
            ));
        } 
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> UserTypeRegister(@RequestBody UserType userType) throws CustomException {
        
        if(userType == null || userType.getType() == null){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(
                env.getProperty("http.client.bad-request"), 
                HttpStatus.BAD_REQUEST.value()
            ));
        }

        UserType userTypeRegistered = userTypeServiceImpl.registerUserType(userType);

        if(userTypeRegistered == null){
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(
                env.getProperty("database.create-failed"),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
            ));
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(
            userTypeRegistered,
            env.getProperty("http.success.created"),
            HttpStatus.CREATED.value()
        ));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateUserType(@RequestBody UserType userType) throws CustomException {
        
        if (userType == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(

                env.getProperty("http.client.bad-request"), 
                HttpStatus.BAD_REQUEST.value()
            ));
        }

        UserType userTypeUpdated = userTypeServiceImpl.updateUserType(userType);

        if (userTypeUpdated == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(

                env.getProperty("database.update-failed"),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
            ));
        }
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseDto(
            
            userTypeUpdated,
            env.getProperty("http.success.accepted"),
            HttpStatus.ACCEPTED.value()
        ));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteUserType(@PathVariable Integer id) throws CustomException{

        if (id == null || id == 0) {
            
            throw new CustomException(
                env.getProperty("http.client.bad-request"),
                HttpStatus.BAD_REQUEST
            );
        }

        userTypeServiceImpl.deleteUserType(id);
        
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
            env.getProperty("http.success.ok"),
            HttpStatus.OK.value()
        ));
    }
}
