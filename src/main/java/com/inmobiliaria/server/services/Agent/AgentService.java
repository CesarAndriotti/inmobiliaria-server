package com.inmobiliaria.server.services.Agent;

import java.util.List;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.Agent;

public interface AgentService {

    public List<Agent> getAgentList() throws CustomException;
    public Agent updateAgent(Agent agent) throws CustomException;
}
