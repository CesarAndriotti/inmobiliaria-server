package com.inmobiliaria.server.services.State;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.inmobiliaria.server.dto.State.StateResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.mappers.StateMapper;
import com.inmobiliaria.server.models.State;
import com.inmobiliaria.server.repositories.State.StateRepository;

@Service
public class StateServiceImpl implements StateService{

    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private Environment env;
    @Autowired
    private StateMapper stateMapperImpl;

    @Override
    public List<StateResponse> getAllStates() throws CustomException {
        
        try {
            
            List<State> stateList = stateRepository.findAll();

            List<StateResponse> stateDtos = stateMapperImpl.toDtoList(stateList);
            return stateDtos;
            
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
