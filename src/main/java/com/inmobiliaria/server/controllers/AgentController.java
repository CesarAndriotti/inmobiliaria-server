package com.inmobiliaria.server.controllers;

import java.util.Date;
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
    public ResponseEntity<ResponseDto> firstRegister(@RequestBody UserDto userDto){

        try {
            if (userDto == null || userDto.getAgent() == null) {
                
                return ResponseEntity.badRequest().body(new ResponseDto(
                    "There are null data in the fields, please, verify and try again.", null, HttpStatus.BAD_REQUEST.value(), new Date()
                ));
            }
            else{

                userDto = agentServiceImpl.registerAgent(userDto);
                
                return ResponseEntity.created(null).body(new ResponseDto(
                    "The agent "+userDto.getAgent().getLastname()+", "+userDto.getAgent().getName()+" and the User "+userDto.getNick()+" have been created", null, HttpStatus.CREATED.value(), new Date()
                ));
            }
        }
        catch (Exception e) {
            
            return ResponseEntity.internalServerError().body(new ResponseDto(
                "", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date()
            ));
        }
    }
}
