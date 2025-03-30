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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inmobiliaria.server.dto.AgentDto;
import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.dto.UserDto;
import com.inmobiliaria.server.services.Agent.AgentServiceImpl;
import org.springframework.web.bind.annotation.PutMapping;
import com.inmobiliaria.server.exceptions.BadRequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/agents")
public class AgentController {

    @Autowired
    AgentServiceImpl agentServiceImpl;
    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/show-list")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> firstRegister(@RequestBody UserDto userDto) {
        
        //Analizar que controllers no son necesarios
        //Continuar estudiando respuestas de errores y mensajes
        //Sacar los campos dto de los models y dto

        if (userDto == null || userDto.getAgent() == null) {

            throw new BadRequestException("Este es mi mensaje");
        }

        Optional<UserDto> userRegistered = agentServiceImpl.registerAgentAndUser(userDto);

        if (userRegistered.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(
                "Agent and user registration failed.",
                null, HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date()
            ));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(
            "The agent " + userRegistered.get().getAgent().getLastname() + ", " + 
            userRegistered.get().getAgent().getName() + " and the user " + 
            userRegistered.get().getNick() + " have been created successfully.",
            null, HttpStatus.CREATED.value(), new Date()
        ));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAgentData(@RequestBody AgentDto agentDto) {

        if (agentDto == null || agentDto.getAddress() == null || agentDto.getAgentState() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(
                "Invalid data Agent or any items is missing",
                null,
                HttpStatus.BAD_REQUEST.value(),
                new Date()
            ));
        }

        Optional<AgentDto> agentUpdated = agentServiceImpl.updateAgentData(agentDto);

        if (agentUpdated.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(
                "Agent update is failed",
                null,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date()
            ));
        }

        if (agentDto == agentUpdated.get()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseDto(
                "The agent "+agentUpdated.get().getLastname()+", " +
                agentUpdated.get().getName()+" hasn't been updated. Your information is the same",
                null,
                HttpStatus.NOT_ACCEPTABLE.value(),
                new Date()
            ));
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseDto(
            "The agent "+agentUpdated.get().getLastname()+", " +
            agentUpdated.get().getName()+" has been updated successfully",
            null,
            HttpStatus.ACCEPTED.value(),
            new Date()
        ));
    }
}


