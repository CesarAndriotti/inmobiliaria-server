package com.inmobiliaria.server.services.Session;

import java.util.List;
import com.inmobiliaria.server.dto.Session.SessionRequest;
import com.inmobiliaria.server.dto.Session.SessionResponse;
import com.inmobiliaria.server.exceptions.CustomException;

public interface SessionService {

    List<SessionResponse> getAllSessions() throws CustomException;
    SessionResponse registerSession(SessionRequest sessionDto) throws CustomException;
    SessionResponse updateSession(Integer id, SessionRequest sessionDt) throws CustomException;
}
