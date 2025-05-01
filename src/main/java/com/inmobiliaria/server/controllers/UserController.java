package com.inmobiliaria.server.controllers;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.User;
import com.inmobiliaria.server.security.JwtUtil;
import com.inmobiliaria.server.services.User.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    Environment env;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerUserAndAgent(@RequestBody User user) throws CustomException{
        
        if(user == null || user.getAgent() == null || user.getAgent().getAddress() == null 
        || user.getAgent().getAgentState() == null || user.getUser_type() == null){
            throw new CustomException(env.getProperty("http.client.bad-request"), HttpStatus.BAD_REQUEST);
        }
        
        User userRegistered = userServiceImpl.registerUserAndAgent(user);

        if (userRegistered == null) throw new CustomException("Error saving entity", HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(

            userRegistered,
            "",
            HttpStatus.CREATED.value()
        ));
    }        
}
