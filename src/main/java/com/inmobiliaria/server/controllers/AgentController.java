package com.inmobiliaria.server.controllers;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.MessageConstants;
import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.exceptions.BadRequestException;
import com.inmobiliaria.server.exceptions.InternalServerErrorException;
import com.inmobiliaria.server.models.Agent;
import com.inmobiliaria.server.models.User;
import com.inmobiliaria.server.services.Agent.AgentServiceImpl;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    @Autowired
    AgentServiceImpl agentServiceImpl;

    @GetMapping("/show-list")
    public ResponseEntity<ResponseDto> showAgentList() {

        List<Agent> agentList = agentServiceImpl.getAgentList();
        
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
            agentList,
            "",
            null,
            HttpStatus.OK.value(),
            new Date()
        ));
    }
    
    @PostMapping("/first-register")
    public ResponseEntity<ResponseDto> firstRegister(@RequestBody User user) {

        if (user == null || user.getAgent() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(
                null,
                MessageConstants.ERROR_BAD_REQUEST,
                null, HttpStatus.BAD_REQUEST.value(), 
                new Date()
            ));
        }

        User userRegistered = agentServiceImpl.firstRegisterAgentAndUser(user);

        if (userRegistered == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(
                null,
                MessageConstants.ERROR_CREATE_FAILED,
                null, HttpStatus.INTERNAL_SERVER_ERROR.value(), 
                new Date()
            ));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(
            null,
            MessageConstants.SUCCESS_CREATE,
            null, HttpStatus.CREATED.value(), 
            new Date()
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerAgent(@RequestBody User user) {

        if (user == null || user.getAgent() == null) throw new BadRequestException(MessageConstants.ERROR_BAD_REQUEST);
        
        User userRegistered = agentServiceImpl.registerAgentAndUser(user);
        
        if (userRegistered == null) throw new InternalServerErrorException(MessageConstants.ERROR_INTERNAL_SERVER);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(
            null,
            MessageConstants.SUCCESS_CREATE,
            null, HttpStatus.CREATED.value(), 
            new Date()
        ));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAgentData(@RequestBody Agent agent) {

        if (agent == null || agent.getAddress() == null || agent.getAgentState() == null) throw new BadRequestException(MessageConstants.ERROR_BAD_REQUEST);
        
        Agent agentUpdated = agentServiceImpl.updateAgentData(agent);
        
        if (agentUpdated == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(
                null,
                MessageConstants.ERROR_UPDATE_FAILED,
                null,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date()
            ));
        }

        if (agent == agentUpdated) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseDto(
                null,
                MessageConstants.IDENTICAL_DATA,
                null,
                HttpStatus.NOT_ACCEPTABLE.value(),
                new Date()
            ));
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseDto(
            null,
            MessageConstants.SUCCESS_UPDATE,
            null,
            HttpStatus.ACCEPTED.value(),
            new Date()
        ));
    }
}


