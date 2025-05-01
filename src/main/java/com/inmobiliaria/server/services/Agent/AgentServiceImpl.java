package com.inmobiliaria.server.services.Agent;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
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
        catch(InternalServerError e){

            throw new CustomException(env.getProperty("http.server.internal-server"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public Agent updateAgentData(Agent agent) throws CustomException{
        
        try {
            Optional <Agent> agentDataBase = agentRepository.findById(agent.getId());
            
            if (!agent.equals(agentDataBase.get()) || !agent.getAddress().equals(agentDataBase.get().getAddress())) { 
                addressRepository.save(agent.getAddress());
                Agent agentUpdated = agentRepository.save(agent);

                return agentUpdated;
            }
            else{
                throw new CustomException("Identical Data", HttpStatus.CONFLICT);
            }
        }
        catch(InternalServerError e){
            throw new CustomException(env.getProperty("http.server.internal-server"), HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }
}


