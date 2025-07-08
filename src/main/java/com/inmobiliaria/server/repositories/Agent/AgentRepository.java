package com.inmobiliaria.server.repositories.Agent;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.server.models.Agent;
import java.sql.Date;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {

    /*@Query(value = """
    SELECT * from user 
    JOIN agent ON agent_id = agent.id
    JOIN agent_state ON agent_state_id = agent_state.id
    JOIN address ON address_id = address.id
    JOIN city ON city_id = city.id
    JOIN state ON state_id = state.Id
    JOIN country ON country_id = country.id
    JOIN user_type ON user_type_id = user_type.id
    """, nativeQuery = true)*/
    List<Agent> findAll();
    Optional<Agent> findByName(String name);
    Optional<Agent> findByLastname(String lastname);
    Optional<Agent> findByDateOfBirth(Date dateOfBirth);
    Optional<Agent> findById(int id);
    Optional<Agent> findByPhoneNumber(String phoneNumber);
    Optional<Agent> findByEmail(String email);
    Optional<Agent> findByAgentRegistration(String agentRegistration);
    Optional<Agent> findByIdentificationNumber(String identificationNumber);

    
    Optional<Agent> findByIdentificationNumberOrEmailOrAgentRegistrationOrPhoneNumber(String identificationNumber, String Email, String agentRegistration, String phoneNumber);

    @Override
    <S extends Agent> S save(S agent); 
}
