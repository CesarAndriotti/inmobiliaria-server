package com.inmobiliaria.server.services.SessionState;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inmobiliaria.server.dto.SessionState.SessionStateRequest;
import com.inmobiliaria.server.dto.SessionState.SessionStateResponse;
import com.inmobiliaria.server.mappers.SessionMapper;
import com.inmobiliaria.server.mappers.SessionStateMapper;
import com.inmobiliaria.server.models.SessionState;
import com.inmobiliaria.server.repositories.SessionState.SessionStateRepository;

@Service
public class SessionStateServiceImpl implements SessionStateService {

    @Autowired
    SessionStateRepository sessionStateRepository;
    @Autowired
    SessionMapper sessionMapper;
    @Autowired
    SessionStateMapper sessionStateMapper;

    @Override
    public List<SessionStateResponse> getAllSessionStates() {
        

        List<SessionState> sessionStates = sessionStateRepository.findAll();
        List<SessionStateResponse> sesssionStateDtos = sessionStateMapper.toDtoList(sessionStates);
        return sesssionStateDtos;
    }
}
