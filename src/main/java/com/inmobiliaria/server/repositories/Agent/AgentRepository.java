package com.inmobiliaria.server.repositories.Agent;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.server.models.Agent;
import java.sql.Date;

@Repository
public interface AgentRepository extends CrudRepository<Agent, Integer> {

    List<Agent> findAll();
    List<Agent> findByName(String name);
    List<Agent> findByLastname(String lastname);
    List<Agent> findByDateOfBirth(Date dateOfBirth);

    Optional<Agent> findById(int id);
    Optional<Agent> findByPhoneNumber(String phoneNumber);
    Optional<Agent> findByIdentificationNumber(String identificationNumber);
    Optional<Agent> findByEmail(String email);
    Optional<Agent> findByAgentRegistration(String agentRegistration);

    @Override
    <S extends Agent> S save(S agent); 
}
