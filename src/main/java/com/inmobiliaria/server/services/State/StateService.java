package com.inmobiliaria.server.services.State;

import java.util.List;

import com.inmobiliaria.server.dto.State.StateResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.State;

public interface StateService{

    List<StateResponse> getAllStates() throws CustomException;
}
