package com.inmobiliaria.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.inmobiliaria.server.models.State;
import com.inmobiliaria.server.services.State.StateServiceImpl;
@RestController
@RequestMapping("/api")
public class StateController {

    @Autowired
    StateServiceImpl stateService;

    @GetMapping("/states-list")
    public List<State> statesList(){

        return stateService.getAllStates();
    }
}
