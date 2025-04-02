package com.inmobiliaria.server.services.Agent;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.models.Agent;
import com.inmobiliaria.server.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inmobiliaria.server.MessageConstants;
import com.inmobiliaria.server.exceptions.ConflictException;
import com.inmobiliaria.server.exceptions.InternalServerErrorException;
import com.inmobiliaria.server.models.Address;
import com.inmobiliaria.server.models.UserType;
import com.inmobiliaria.server.repositories.Address.AddressRepository;
import com.inmobiliaria.server.repositories.Agent.AgentRepository;
import com.inmobiliaria.server.repositories.User.UserRepository;
import com.inmobiliaria.server.services.User.UserServiceImpl;
import jakarta.transaction.Transactional;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public List<Agent> getAgentList() {

        try {
            List<Agent> agentList = agentRepository.findAll();
            return agentList; 
            
        } catch (Exception e) {
            
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public User firstRegisterAgentAndUser(User user) {

        try {
            Address newAddress = addressRepository.save(user.getAgent().getAddress());
            Agent agentToRegister = user.getAgent();
            agentToRegister.setAddress(newAddress);
            Agent newAgent = agentRepository.save(agentToRegister);
            User newUser = userServiceImpl.registerUser(user);
            UserType userType = new UserType();
            userType.setId(1);
            user.setUser_type(userType);
            user.setAgent(newAgent);

            return newUser;

        } catch (Exception e) {
            
            throw new InternalServerErrorException(MessageConstants.ERROR_INTERNAL_SERVER + " " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public User registerAgentAndUser(User user) {
    
        try {

            Address newAddress = addressRepository.save(user.getAgent().getAddress());
            Agent agentToRegister = user.getAgent();

            Optional<Agent> agentDatabase = agentRepository.findByIdentificationNumber(agentToRegister.getIdentificationNumber());

            if (agentDatabase.isPresent()) {
                throw new ConflictException("The agent is already registred. "+MessageConstants.EXISTING_DATA);
            }

            agentToRegister.setAddress(newAddress);
            
            Optional<User> userDatabase = userRepository.findByNick(user.getNick());

            if (userDatabase.isPresent()) {
                throw new ConflictException("The user is already registred. "+MessageConstants.EXISTING_DATA);
            }

            Agent newAgent = agentRepository.save(agentToRegister);
            User newUser = userServiceImpl.registerUser(user);
            UserType userType = new UserType();
            userType.setId(user.getUser_type().getId());
            user.setUser_type(userType);
            user.setAgent(newAgent);

            return newUser;

        } catch (ConflictException e) {
            
            throw e;
        }
        catch (Exception e) {
            
            throw new InternalServerErrorException(MessageConstants.ERROR_INTERNAL_SERVER+". "+e.getMessage());
        }   
    }

    @Override
    @Transactional
    public Agent updateAgentData(Agent agent){
        
        try {
            Optional <Agent> agentDataBase = agentRepository.findById(agent.getId());
            
            if (!agent.equals(agentDataBase.get())) { 
                if (!agent.getAddress().equals(agentDataBase.get().getAddress())) {
                    addressRepository.save(agent.getAddress());
                }
                Agent agentUpdated = agentRepository.save(agent);

                return agentUpdated;
            }
            else{
                throw new ConflictException(MessageConstants.IDENTICAL_DATA);
            }

        } catch (ConflictException e) {
            
            throw e;
        }
        catch (Exception e) {
            
            throw new InternalServerErrorException(MessageConstants.ERROR_INTERNAL_SERVER+". "+e.getMessage());
        }        
    }
}


