package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

import com.inmobiliaria.server.dto.UserTypeDto;
import com.inmobiliaria.server.models.UserType;
import com.inmobiliaria.server.services.UserType.UserTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<UserType> postUserType(@RequestBody UserTypeDto userTypeDto) {
        
        Optional <UserType> newUserType = userTypeServiceImpl.registerUserType(userTypeDto);
        
        //return ResponseEntity.ok(newUserType.get());

        return null;
    }
}
