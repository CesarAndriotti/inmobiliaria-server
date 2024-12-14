package com.inmobiliaria.server.services.State;

import java.util.List;
import java.util.Optional;
import com.inmobiliaria.server.models.State;

public interface StateService{

    List<State> getAllStates();
    Optional<State> saveState(State State);
    Optional<State> getStateById(Integer id);
    void deleteState(Integer id);
}
