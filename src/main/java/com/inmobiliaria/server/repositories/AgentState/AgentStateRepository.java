package com.inmobiliaria.server.repositories.AgentState;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.inmobiliaria.server.models.AgentState;

public interface AgentStateRepository extends JpaRepository <AgentState, Integer> {

    Optional<AgentState> findByName(String name);
}
