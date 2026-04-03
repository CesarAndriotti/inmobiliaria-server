package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.Session.SessionRequest;
import com.inmobiliaria.server.dto.Session.SessionResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.services.Session.SessionServiceImpl;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    @Autowired
    Environment env;
    @Autowired
    SessionServiceImpl sessionServiceImpl;

    @GetMapping("/show-list")
    public ResponseEntity<List<SessionResponse>> getSessionList() throws CustomException {

        List<SessionResponse> sessionList = sessionServiceImpl.getAllSessions();
        
        return ResponseEntity.status(HttpStatus.OK).body(sessionList);
    }

    @PostMapping("/register")
    public ResponseEntity<SessionResponse> registerSession (@RequestBody @Valid SessionRequest sessionRequestDto) throws CustomException {
        
        SessionResponse registeredSession = sessionServiceImpl.registerSession(sessionRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredSession);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<SessionResponse> updateSession(@PathVariable @Valid Integer id, SessionRequest sessionRequest) throws CustomException{
        
        SessionResponse sessionResponse = sessionServiceImpl.updateSession(id, sessionRequest);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(sessionResponse);
    }   
}
