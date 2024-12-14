package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.inmobiliaria.server.models.UserType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/usertypes")
public class UserTypeController {

    

    @PostMapping("/register")
    public String postMethodName(@RequestBody String userType) {
        
        
        
        return null;
    }

}
