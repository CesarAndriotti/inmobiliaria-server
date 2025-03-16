package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.Optional;
import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.dto.UserTypeDto;
import com.inmobiliaria.server.services.UserType.UserTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/usertypes")
public class UserTypeController {

    @Autowired
    UserTypeServiceImpl userTypeServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> UserTypeRegister(@RequestBody UserTypeDto userTypeDto) {
        
        if(userTypeDto == null || userTypeDto.getType() == null){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(
                "Invalid data: UserType data is missing, please check the fields.",
                null, 
                HttpStatus.BAD_REQUEST.value(), 
                new Date()
            ));
        }

        Optional <UserTypeDto> userTypeRegistered = userTypeServiceImpl.registerUserType(userTypeDto);

        if(userTypeRegistered.isEmpty()){
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(
                "Error: The userType hasn't been registered",
                null,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date()
            ));
        }
        else{
            
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(
                "Success: The userType has been created successfully",
                null,
                HttpStatus.CREATED.value(),
                new Date()
            ));
        }
    }
}
