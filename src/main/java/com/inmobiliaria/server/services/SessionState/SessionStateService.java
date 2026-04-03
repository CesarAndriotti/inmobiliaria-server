package com.inmobiliaria.server.services.SessionState;

import java.util.List;
import com.inmobiliaria.server.dto.SessionState.SessionStateRequest;
import com.inmobiliaria.server.dto.SessionState.SessionStateResponse;

public interface SessionStateService {

    List<SessionStateResponse> getAllSessionStates();
}
