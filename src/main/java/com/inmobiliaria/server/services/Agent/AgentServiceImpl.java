package com.inmobiliaria.server.services.Agent;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.models.Agent;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.repositories.Address.AddressRepository;
import com.inmobiliaria.server.repositories.Agent.AgentRepository;
import jakarta.transaction.Transactional;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private Environment env;
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Agent> getAgentList() throws CustomException {
        
        try {
            List<Agent> agents = agentRepository.findAll();
            return agents;
        } 
        catch(DataAccessException e){

            throw new CustomException(
                env.getProperty("database.access-error")+": "+e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        catch(Exception e){

            throw new CustomException(
                env.getProperty("unhandled-exception")+": "+e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    //EntityNotFoundException para una busqueda con id

    @Override
    @Transactional
    public Agent updateAgent(Agent agent) throws CustomException{
        
        try {
            Optional <Agent> agentDataBase = agentRepository.findById(agent.getId());
        
            if (agentDataBase.isEmpty()) {
                throw new CustomException(
                    env.getProperty("database.entity-not-found"),
                    HttpStatus.NOT_FOUND
                );
            }

            Agent existingAgent = agentDataBase.get();

            boolean isSameAgent = agent.equals(existingAgent);
            boolean isSameAddress = agent.getAddress().equals(existingAgent.getAddress());

            if (isSameAgent && isSameAddress) {
                
                throw new CustomException(
                    env.getProperty("database.identical-data"),
                    HttpStatus.CONFLICT
                );
            }

            addressRepository.save(agent.getAddress());
            Agent agentUpdated = agentRepository.save(agent);

            return agentUpdated;
        } 
        catch(DataAccessException e){

            throw new CustomException(
                env.getProperty("database.access-error")+": "+e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        catch(Exception e){

            throw new CustomException(
                env.getProperty("unhandled-exception")+": "+e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}


