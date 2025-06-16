package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.models.AgentState;
import com.inmobiliaria.server.repositories.AgentState.AgentStateRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
@RestController
@RequestMapping("/api")
public class AgentStateController {
    
    @Autowired
    AgentStateRepository agentStateRepository;

    @GetMapping("/agent-states")
    public List<AgentState> getAgentStates() {
        
        return agentStateRepository.findAll();
    }
}
