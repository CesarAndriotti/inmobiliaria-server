package com.inmobiliaria.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.inmobiliaria.server.models.State;
import com.inmobiliaria.server.repositories.State.StateRepository;

@RestController
@RequestMapping("/api/states")
public class StateController {

    @Autowired
    StateRepository stateRepository;

    @GetMapping("/show-list")
    public List<State> getStatesList(){

        return stateRepository.findAll();
    }
}
