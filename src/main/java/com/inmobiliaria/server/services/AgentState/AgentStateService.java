package com.inmobiliaria.server.services.AgentState;

import java.util.List;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.AgentState;

public interface AgentStateService {

    public AgentState registerAgentState(AgentState State) throws CustomException;
    public List<AgentState> getAllAgentStates() throws CustomException;
    public AgentState updateAgentState(AgentState agentState) throws CustomException;
    public void deleteAgentState(Integer id) throws CustomException;
}
