package com.inmobiliaria.server.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inmobiliaria.server.dto.User.UserResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.User;
import com.inmobiliaria.server.services.User.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    Environment env;

    @GetMapping("/show-list")
    public ResponseEntity <List<UserResponse>> getUserList() throws CustomException {

        List<UserResponse> userList = userServiceImpl.getAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }
}
