package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.inmobiliaria.server.dto.UserType.UserTypeResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.services.UserType.UserTypeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/user-types")
public class UserTypeController {

    @Autowired
    UserTypeServiceImpl userTypeServiceImpl;
    @Autowired
    Environment env;

    @GetMapping("/show-list")
    public ResponseEntity<List<UserTypeResponse>> getUserTypeList() throws CustomException {

        List<UserTypeResponse> userTypeListed = userTypeServiceImpl.getAllUserTypes();

        return ResponseEntity.status(HttpStatus.OK).body(userTypeListed);

    }

    @PostMapping("/register")
    public ResponseEntity<UserTypeResponse> postUserTypeRegister(@RequestBody @Valid UserTypeResponse userTypeDto) throws CustomException {

        UserTypeResponse userTypeRegistered = userTypeServiceImpl.registerUserType(userTypeDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(userTypeRegistered);
    }

    @PutMapping("/update")
    public ResponseEntity<UserTypeResponse> putUserType(@RequestBody @Valid UserTypeResponse userTypeDto) throws CustomException {

        UserTypeResponse userTypeUpdated = userTypeServiceImpl.updateUserType(userTypeDto);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userTypeUpdated);
    }
}
