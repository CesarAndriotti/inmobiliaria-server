package com.inmobiliaria.server.controllers;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.dto.UserDto;
import com.inmobiliaria.server.services.Agent.AgentServiceImpl;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    @Autowired
    AgentServiceImpl agentServiceImpl;

    @PostMapping("/first-register")
    public ResponseEntity<ResponseDto> firstRegister(@RequestBody UserDto userDto) {
        if (userDto == null || userDto.getAgent() == null) {
            return ResponseEntity.badRequest().body(new ResponseDto(
                "Invalid data: user or agent is missing.",
                null, HttpStatus.BAD_REQUEST.value(), new Date()
            ));
        }

        Optional<UserDto> registeredUser = agentServiceImpl.registerAgentAndUser(userDto);

        if (registeredUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(
                "Agent and user registration failed.",
                null, HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date()
            ));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(
            "The agent " + registeredUser.get().getAgent().getLastname() + ", " + 
            registeredUser.get().getAgent().getName() + " and the user " + 
            registeredUser.get().getNick() + " have been created successfully.",
            null, HttpStatus.CREATED.value(), new Date()
        ));
    }
}


