package com.inmobiliaria.server.services.Agent;

import java.util.List;

import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.Agent;
import com.inmobiliaria.server.models.User;

public interface AgentService {

    public List<Agent> getAgentList() throws CustomException;
    public Agent updateAgentData(Agent agent) throws CustomException;
}
