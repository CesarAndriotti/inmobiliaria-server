package com.inmobiliaria.server.services.State;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inmobiliaria.server.models.State;
import com.inmobiliaria.server.repositories.State.StateRepository;

@Service
public class StateServiceImpl implements StateService{

    @Autowired
    private StateRepository stateRepository;

    @Override
    public List<State> getAllStates() {
        return stateRepository.findAll();
    }

    @Override
    public Optional<State> saveState(State state) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveState'");
    }

    @Override
    public Optional<State> getStateById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStateById'");
    }

    @Override
    public void deleteState(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteState'");
    }

}
