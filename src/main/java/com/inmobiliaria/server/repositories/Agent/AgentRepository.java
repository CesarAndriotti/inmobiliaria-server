package com.inmobiliaria.server.repositories.Agent;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.server.models.Agent;
import java.sql.Date;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {

    List<Agent> findAll();
    
    Optional<Agent> findByName(String name);
    Optional<Agent> findByLastname(String lastname);
    Optional<Agent> findByDateOfBirth(Date dateOfBirth);
    Optional<Agent> findById(int id);
    Optional<Agent> findByPhoneNumber(String phoneNumber);
    Optional<Agent> findByEmail(String email);
    Optional<Agent> findByAgentRegistration(String agentRegistration);
    Optional<Agent> findByIdentificationNumber(String identificationNumber);

    @Override
    <S extends Agent> S save(S agent); 
}
