package com.inmobiliaria.server.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inmobiliaria.server.dto.Agent.AgentRequest;
import com.inmobiliaria.server.dto.Agent.AgentResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.services.Agent.AgentServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    @Autowired
    AgentServiceImpl agentServiceImpl;
    @Autowired
    Environment env;

    @GetMapping("/show-list")
    public ResponseEntity<List<AgentResponse>> getAgentList() throws CustomException {

        List<AgentResponse> agentList = agentServiceImpl.getAllAgents();

        return ResponseEntity.status(HttpStatus.OK).body(agentList);
    }
/* 
    @PutMapping("/update")
    public ResponseEntity<AgentResponse> putAgent(@RequestBody @Valid Agent agent) throws CustomException {
        
        AgentResponse agentUpdated = agentServiceImpl.updateAgent(agent);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(agentUpdated);
    }*/

    @PostMapping("/register")
    public ResponseEntity<AgentResponse> postUserAndAgent(@RequestBody @Valid AgentRequest agentRequest) throws CustomException{
        
        AgentResponse agentRegistered = agentServiceImpl.registerAgentAndUser(agentRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(agentRegistered);
    } 
}


