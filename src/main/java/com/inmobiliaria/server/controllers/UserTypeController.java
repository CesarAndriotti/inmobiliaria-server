package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.List;
import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.models.UserType;
import com.inmobiliaria.server.services.UserType.UserTypeServiceImpl;
import jakarta.persistence.QueryTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/usertypes")
public class UserTypeController {

    @Autowired
    UserTypeServiceImpl userTypeServiceImpl;

    @GetMapping("/show-list")
    public ResponseEntity<ResponseDto> getUserTypeList() {

        try {
            List<UserType> userTypeListed = userTypeServiceImpl.showUserTypeList();

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
                userTypeListed,
                "",
                HttpStatus.OK.value()
            ));
        }
        catch (QueryTimeoutException e) {
            
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new ResponseDto<>(
                null,
                "",
                HttpStatus.GATEWAY_TIMEOUT.value()
            ));
        } 
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> UserTypeRegister(@RequestBody UserType userType) {
        
        if(userType == null || userType.getType() == null){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(
                null,
                "", 
                HttpStatus.BAD_REQUEST.value()
            ));
        }

        UserType userTypeRegistered = userTypeServiceImpl.registerUserType(userType);

        if(userTypeRegistered == null){
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(
                null,
                "",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
            ));
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(
            userTypeRegistered,
            "",
            HttpStatus.CREATED.value()
        ));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateUserType(@RequestBody UserType userType) {
        
        if (userType == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(

                null,
                "",
                HttpStatus.BAD_REQUEST.value()
            ));
        }

        UserType userTypeUpdated = userTypeServiceImpl.updateUserType(userType);

        if (userTypeUpdated == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(

                null,
                "",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
            ));
        }
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseDto(
            
            userTypeUpdated,
            "",
            HttpStatus.ACCEPTED.value()
        ));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteUserType(@RequestParam(required = false) Integer id){

        userTypeServiceImpl.deleteUserType(id);
        
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
            null,
            "",
            HttpStatus.OK.value()
        ));
    }
}
