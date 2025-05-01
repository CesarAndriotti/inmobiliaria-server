package com.inmobiliaria.server.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.dto.UserDto;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.repositories.User.UserRepository;
import com.inmobiliaria.server.services.User.UserServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Environment env;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody UserDto userDto) throws CustomException {

        if (userDto == null || userDto.getNick().isEmpty() || userDto.getPassword().isEmpty()) {
            throw new CustomException("Nick or password cannot be empty", HttpStatus.BAD_REQUEST);
        }

        //Llama al servicio de autenticación
        Map<String, Object> response = userServiceImpl.authenticateUser(userDto.getNick(), userDto.getPassword());

        return ResponseEntity.ok(new ResponseDto(
            response,
            env.getProperty("login-successful"),
            HttpStatus.OK.value()
        ));
    }
}


