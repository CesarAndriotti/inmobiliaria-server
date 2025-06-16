package com.inmobiliaria.server.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.Agent;
import com.inmobiliaria.server.services.Agent.AgentServiceImpl;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    @Autowired
    AgentServiceImpl agentServiceImpl;
    @Autowired
    Environment env;

    @GetMapping("/show-list")
    public ResponseEntity<ResponseDto> getAgentList() throws CustomException {

        List<Agent> agentList = agentServiceImpl.getAgentList();
        
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
            agentList,
            env.getProperty("http.success.ok"),
            HttpStatus.OK.value()
        ));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> putAgentData(@RequestBody Agent agent) throws CustomException {

        if (agent == null || agent.getAddress() == null || agent.getAgentState() == null) {
            throw new CustomException(
                env.getProperty("http.client.bad-request"), 
                HttpStatus.BAD_REQUEST
            );
        }
        
        Agent agentUpdated = agentServiceImpl.updateAgentData(agent);
        
        if (agentUpdated == null) {
            throw new CustomException(
                env.getProperty("http.server.internal-server"), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseDto<>(
                agentUpdated,
                env.getProperty("http.success.accepted"),
                HttpStatus.ACCEPTED.value()
            ));
        }
    }
}


