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
import com.inmobiliaria.server.models.User;
import com.inmobiliaria.server.services.Agent.AgentServiceImpl;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    @Autowired
    AgentServiceImpl agentServiceImpl;
    @Autowired
    Environment env;

    @GetMapping("/show-list")
    public ResponseEntity<ResponseDto> getAgentList() throws CustomException {

        List<Agent> agentList = agentServiceImpl.getAllAgents();

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
            agentList,
            env.getProperty("http.success.ok"),
            HttpStatus.OK.value()
        ));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> putAgent(@RequestBody Agent agent) throws CustomException {

        if (agent == null || agent.getAddress() == null || agent.getAgentState() == null) {
            throw new CustomException(
                env.getProperty("http.client.bad-request"), 
                HttpStatus.BAD_REQUEST
            );
        }
        
        Agent agentUpdated = agentServiceImpl.updateAgent(agent);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseDto<>(
            agentUpdated,
            env.getProperty("http.success.accepted"),
            HttpStatus.ACCEPTED.value()
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> postUserAndAgent(@RequestBody User user) throws CustomException{
        
        if(user == null || user.getAgent() == null || user.getAgent().getAddress() == null 
        || user.getAgent().getAgentState() == null || user.getUser_type() == null){

            throw new CustomException(
                env.getProperty("http.client.bad-request"), 
                HttpStatus.BAD_REQUEST
            );
        }
        
        User userRegistered = agentServiceImpl.registerAgentAndUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto<>(

            userRegistered,
            env.getProperty("http.success.created"),
            HttpStatus.CREATED.value()
        ));
    } 
}


