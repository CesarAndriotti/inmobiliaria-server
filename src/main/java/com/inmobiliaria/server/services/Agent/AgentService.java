package com.inmobiliaria.server.services.Agent;

import java.util.List;
import com.inmobiliaria.server.models.Agent;
import com.inmobiliaria.server.models.User;

public interface AgentService {

    public List<Agent> getAgentList();
    public User registerAgentAndUser(User user);
    public Agent updateAgentData(Agent agent);
    public User firstRegisterAgentAndUser(User user);
}
