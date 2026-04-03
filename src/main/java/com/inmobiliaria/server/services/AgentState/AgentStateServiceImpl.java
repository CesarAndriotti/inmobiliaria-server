package com.inmobiliaria.server.services.AgentState;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.dto.AgentState.AgentStateResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.mappers.AgentStateMapper;
import com.inmobiliaria.server.models.AgentState;
import com.inmobiliaria.server.repositories.AgentState.AgentStateRepository;;

@Service
public class AgentStateServiceImpl implements AgentStateService {

    @Autowired
    AgentStateRepository agentStateRepository;
    @Autowired
    Environment env;
    @Autowired
    AgentStateMapper agentStateMapper;

    @Override
    public List<AgentStateResponse> getAllAgentStates() throws CustomException {

        try {
            List<AgentState> agentList = agentStateRepository.findAll();
            
            return agentStateMapper.toDtoList(agentList);
        } catch (DataAccessException e) {

            throw new CustomException(
                    env.getProperty("data.access-error") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new CustomException(
                    env.getProperty("unhandled-exception") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
