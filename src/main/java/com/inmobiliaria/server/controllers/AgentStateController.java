package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.AgentState;
import com.inmobiliaria.server.services.AgentState.AgentStateServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//Actualizado

@RestController
@RequestMapping("/api/agent-states")
public class AgentStateController {
    
    @Autowired
    AgentStateServiceImpl agentStateServiceImpl;
    @Autowired
    Environment env;

    @GetMapping("/show-list")
    public ResponseEntity<ResponseDto> getAgentStateList() throws CustomException {

        List<AgentState> agentStateList = agentStateServiceImpl.getAllAgentStates();
        
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
            agentStateList,
            env.getProperty("http.success.ok"),
            HttpStatus.OK.value()
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> postAgentState(@RequestBody AgentState agentState) throws CustomException {
        
        if (agentState == null) {
            
            throw new CustomException(
                env.getProperty("http.client.bad-request"), 
                HttpStatus.BAD_REQUEST
            );
        }
        
        AgentState registeredAgentState = agentStateServiceImpl.registerAgentState(agentState);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto<>(

            registeredAgentState,
            env.getProperty("http.success.ok"),
            HttpStatus.CREATED.value()
        ));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> putAgentState(@RequestBody AgentState agentState) throws CustomException{

        if (agentState == null) {
            throw new CustomException(

                env.getProperty("http.client.bad-request"), 
                HttpStatus.BAD_REQUEST
            );
        }

        AgentState updatedAgentState = agentStateServiceImpl.updateAgentState(agentState);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(

            updatedAgentState,
            env.getProperty("http.success.accepted"),
            HttpStatus.ACCEPTED.value()
        ));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteAgentState(@PathVariable Integer id) throws CustomException {

        if (id == null || id == 0) {
            
            throw new CustomException(
                env.getProperty("http.client.bad-request"),
                HttpStatus.BAD_REQUEST
            );
        }

        agentStateServiceImpl.deleteAgentState(id);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(

            env.getProperty("http.success.ok"),
            HttpStatus.OK.value()
        ));
    }
}
