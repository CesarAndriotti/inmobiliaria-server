package com.inmobiliaria.server.repositories.Agent;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.server.models.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {

    List<Agent> findAll();
    Optional<Agent> findByIdentificationNumberOrEmailOrAgentRegistrationOrPhoneNumber(String identificationNumber, String Email, String agentRegistration, String phoneNumber);
    <S extends Agent> S save(S agent); 
}
