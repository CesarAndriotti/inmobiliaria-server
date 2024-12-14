package com.inmobiliaria.server.services.AgentState;

import java.util.List;
import java.util.Optional;
import com.inmobiliaria.server.models.State;

public interface AgentStateService {

    List<State> getAllAgentStates();
    Optional<State> saveAgentState(State State);
    Optional<State> getAgentStateById(Integer id);
    void deleteAgentState(Integer id);
}
