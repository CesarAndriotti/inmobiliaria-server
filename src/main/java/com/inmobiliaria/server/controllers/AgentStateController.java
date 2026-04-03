package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.AgentState.AgentStateResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.services.AgentState.AgentStateServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/agent-states")
public class AgentStateController {
    
    @Autowired
    AgentStateServiceImpl agentStateServiceImpl;
    @Autowired
    Environment env;

    @GetMapping("/show-list")
    public ResponseEntity<List<AgentStateResponse>> getAgentStateList() throws CustomException {

        List<AgentStateResponse> agentStateList = agentStateServiceImpl.getAllAgentStates();
        
        return ResponseEntity.status(HttpStatus.OK).body(agentStateList);
    }
}
