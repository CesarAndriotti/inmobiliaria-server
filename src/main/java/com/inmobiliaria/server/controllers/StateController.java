package com.inmobiliaria.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.inmobiliaria.server.dto.State.StateResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.services.State.StateServiceImpl;

@RestController
@RequestMapping("/api/states")
public class StateController {

    @Autowired
    StateServiceImpl stateServiceImpl;
    @Autowired
    Environment env;

    @GetMapping("/show-list")
    public ResponseEntity<List<StateResponse>> getStatesList() throws CustomException{

        List<StateResponse> stateList = stateServiceImpl.getAllStates();

        return ResponseEntity.status(HttpStatus.OK).body(stateList);
    }
}
