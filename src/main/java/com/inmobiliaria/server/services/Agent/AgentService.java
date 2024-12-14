package com.inmobiliaria.server.services.Agent;

import java.util.List;
import java.util.Optional;
import com.inmobiliaria.server.models.Agent;
import com.inmobiliaria.server.dto.UserDto;

public interface AgentService {

    public UserDto registerAgent(UserDto userDto);
    public List<Agent> createAgentList();
    public List<Agent> getByLastname(String lastname);
    public Optional<Agent> getByIdentificationNumber(String identificationNumber);
    public Optional<Agent> getByEmail(String email);
}
