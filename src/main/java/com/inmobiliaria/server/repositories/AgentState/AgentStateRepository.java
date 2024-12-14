package com.inmobiliaria.server.repositories.AgentState;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.inmobiliaria.server.models.AgentState;

public interface AgentStateRepository extends CrudRepository <AgentState, Integer> {

    @Override
    List<AgentState> findAll();
}
