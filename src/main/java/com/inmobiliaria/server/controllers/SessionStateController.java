package com.inmobiliaria.server.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.SessionState.SessionStateResponse;
import com.inmobiliaria.server.services.SessionState.SessionStateServiceImpl;

@RestController
@RequestMapping("/api/session-states")
public class SessionStateController {

    @Autowired
    Environment env;
    @Autowired
    SessionStateServiceImpl sessionServiceImpl;

    @GetMapping("/show-list")
    public ResponseEntity<List<SessionStateResponse>> getSessionList() {

        List<SessionStateResponse> sessionList = sessionServiceImpl.getAllSessionStates();

        return ResponseEntity.status(HttpStatus.OK).body(sessionList);
    }
}
