package com.inmobiliaria.server.services.Agent;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.models.Address;
import com.inmobiliaria.server.models.Agent;
import com.inmobiliaria.server.models.User;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.repositories.Agent.AgentRepository;
import com.inmobiliaria.server.services.Address.AddressServiceImpl;
import com.inmobiliaria.server.services.User.UserServiceImpl;
import jakarta.transaction.Transactional;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private Environment env;
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private AddressServiceImpl addressServiceImpl;
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired

    @Override
    public List<Agent> getAllAgents() throws CustomException {
        
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

            if (isSameAgent || isSameAddress) {
                
                throw new CustomException(
                    env.getProperty("database.identical-data"),
                    HttpStatus.CONFLICT
                );
            }

            addressServiceImpl.saveAddress(agent.getAddress());
            Agent agentUpdated = agentRepository.save(agent);

            return agentUpdated;
        } 
        catch (DataIntegrityViolationException e) {
            throw new CustomException(
                env.getProperty("database.data-integrity-violation")+": "+e.getMessage(), 
                HttpStatus.CONFLICT);
        }
        catch (DataAccessException e) {
            throw new CustomException(
                env.getProperty("data.access-error")+": "+e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
         catch (Exception e) {
            throw new CustomException(
                env.getProperty("unhadled-error")+": "+e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public User registerAgentAndUser(User user) throws CustomException {

        try { 
            List<User> userDatabase = userServiceImpl.getAllUsers();
            if(userDatabase.isEmpty()) user.getUser_type().setId(1);
                
            boolean nickExists = userDatabase.stream()
            .anyMatch(u -> u.getNick().equals(user.getNick()));

            if (nickExists) {
                throw new CustomException(
                    env.getProperty("database.existing-data") + " Data: " + user.getNick(),
                    HttpStatus.CONFLICT
                );
            }
            
            Optional<Agent> agentDatabase = agentRepository.findByIdentificationNumberOrEmailOrAgentRegistrationOrPhoneNumber(
                user.getAgent().getIdentificationNumber(), 
                user.getAgent().getEmail(),
                user.getAgent().getAgentRegistration(), 
                user.getAgent().getPhoneNumber());

            String fieldName = "";
            
            if (agentDatabase.isPresent()) {
                
                Agent agent = agentDatabase.get();
                if (agent.getIdentificationNumber().equals(user.getAgent().getIdentificationNumber())) fieldName = "Identification Number.";
                if (agent.getEmail().equals(user.getAgent().getEmail())) fieldName = "Email.";
                if (agent.getAgentRegistration().equals(user.getAgent().getAgentRegistration())) fieldName = "Agent Registration.";
                if (agent.getPhoneNumber().equals(user.getAgent().getPhoneNumber())) fieldName = "Phone Number.";
                if (fieldName!="") {
                        throw new CustomException(
                        env.getProperty("database.existing-data") + " Data: " + fieldName,
                        HttpStatus.CONFLICT
                    );
                }
            }

            Address address = user.getAgent().getAddress();
            Optional <Address> existingAddress = addressServiceImpl.getAddressByStreetnameAndNumber(user.getAgent().getAddress());
            
            if (existingAddress.isPresent()) user.getAgent().setAddress(existingAddress.get());
            else addressServiceImpl.saveAddress(address);

            agentRepository.save(user.getAgent());

            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            User newUser = userServiceImpl.saveUser(user);

            return newUser;
        } 
        catch (DataIntegrityViolationException e) {
            throw new CustomException(
                env.getProperty("database.data-integrity-violation")+": "+e.getMessage(), 
                HttpStatus.CONFLICT);
        }
        catch (DataAccessException e) {
            throw new CustomException(
                env.getProperty("data.access-error")+": "+e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
         catch (Exception e) {
            throw new CustomException(
                env.getProperty("unhadled-error")+": "+e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


