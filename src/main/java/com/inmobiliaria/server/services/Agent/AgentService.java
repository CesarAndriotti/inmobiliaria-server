package com.inmobiliaria.server.services.Agent;

import java.util.List;

import com.inmobiliaria.server.dto.Agent.AgentRequest;
import com.inmobiliaria.server.dto.Agent.AgentResponse;
import com.inmobiliaria.server.exceptions.CustomException;

public interface AgentService {

    public List<AgentResponse> getAllAgents() throws CustomException;
    public AgentResponse registerAgentAndUser(AgentRequest agentRequest) throws CustomException;
}
