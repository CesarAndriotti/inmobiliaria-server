package com.inmobiliaria.server.services.AgentState;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.AgentState;
import com.inmobiliaria.server.repositories.AgentState.AgentStateRepository;;

@Service
public class AgentStateServiceImpl implements AgentStateService{

    @Autowired
    AgentStateRepository agentStateRepository;
    @Autowired
    Environment env;

    @Override
    public List<AgentState> getAllAgentStates() throws CustomException {
        
        try {
            List<AgentState> agentStateList = agentStateRepository.findAll();
            return agentStateList;
        } 
        catch (DataAccessException e) {
            
            throw new CustomException(
                env.getProperty("data.access-error")+": "+e.getMessage(),
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

    @Override
    public AgentState registerAgentState(AgentState agentState) throws CustomException {

        try {

            Optional<AgentState> agentStateDatabase = agentStateRepository.findById(agentState.getId());

            if (agentStateDatabase.isPresent()) {
                
                throw new CustomException(
                    env.getProperty("database.existing-data"), 
                    HttpStatus.CONFLICT
                );
            }

            AgentState registeredAgentState = agentStateRepository.save(agentState);

            return registeredAgentState;
        }
        catch (DataIntegrityViolationException e) {
            throw new CustomException(
                env.getProperty("database.data-integrity-violation")+": "+e.getMessage(), 
                HttpStatus.CONFLICT
            );
        }
        catch (DataAccessException e) {
            throw new CustomException(
                env.getProperty("data.access-error")+": "+e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        catch (Exception e) {
            throw new CustomException(
                env.getProperty("unhadled-error")+": "+e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    public AgentState updateAgentState(AgentState agentState) throws CustomException {
        
        try {

            Optional<AgentState> agentStateDatabase = agentStateRepository.findById(agentState.getId());

            if (agentStateDatabase.isPresent()) {
                
                throw new CustomException(
                    env.getProperty("database.existing-data"), 
                    HttpStatus.CONFLICT
                );
            }
            
            AgentState updatedAgentState = agentStateRepository.save(agentState);
            return updatedAgentState;

        } 
        catch (DataIntegrityViolationException e) {
            throw new CustomException(
                env.getProperty("database.data-integrity-violation")+": "+e.getMessage(), 
                HttpStatus.CONFLICT
            );
        }
        catch (DataAccessException e) {
            throw new CustomException(
                env.getProperty("data.access-error")+": "+e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        catch (Exception e) {
            throw new CustomException(
                env.getProperty("unhadled-error")+": "+e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    public void deleteAgentState(Integer id) throws CustomException {

        try {

            agentStateRepository.deleteById(id);

        } 
        catch (DataIntegrityViolationException e) {
            throw new CustomException(
                env.getProperty("database.data-integrity-violation")+": "+e.getMessage(), 
                HttpStatus.CONFLICT
            );
        }
        catch (DataAccessException e) {
            throw new CustomException(
                env.getProperty("data.access-error")+": "+e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        catch (Exception e) {
            throw new CustomException(
                env.getProperty("unhadled-error")+": "+e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
