package com.inmobiliaria.server.services.AgentState;

import java.util.List;
import com.inmobiliaria.server.dto.AgentState.AgentStateRequest;
import com.inmobiliaria.server.dto.AgentState.AgentStateResponse;
import com.inmobiliaria.server.exceptions.CustomException;

public interface AgentStateService {

    public List<AgentStateResponse> getAllAgentStates() throws CustomException;
}
