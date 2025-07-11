package com.inmobiliaria.server.repositories.AgentState;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.inmobiliaria.server.models.AgentState;

public interface AgentStateRepository extends CrudRepository <AgentState, Integer> {

    public List<AgentState> findAll();
    public <S extends AgentState> S save(S agentState);
    public Optional<AgentState> findById(int id);
    public Optional<AgentState> findByState(String type);
    public void deleteById(int id);
}
