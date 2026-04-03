package com.inmobiliaria.server.services.Session;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.dto.Session.SessionRequest;
import com.inmobiliaria.server.dto.Session.SessionResponse;
import com.inmobiliaria.server.dto.User.UserRequest;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.mappers.SessionMapper;
import com.inmobiliaria.server.mappers.UserMapper;
import com.inmobiliaria.server.models.Session;
import com.inmobiliaria.server.models.SessionState;
import com.inmobiliaria.server.models.User;
import com.inmobiliaria.server.repositories.Session.SessionRepository;
import com.inmobiliaria.server.repositories.SessionState.SessionStateRepository;
import com.inmobiliaria.server.repositories.User.UserRepository;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    SessionStateRepository sessionStateRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SessionMapper sessionMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    Environment env;

    @Override
    public List<SessionResponse> getAllSessions() throws CustomException {
        
        List<Session> sessions = sessionRepository.findAll();

        List<SessionResponse> sessionDtos = sessionMapper.toDtoList(sessions);

        return sessionDtos;
    }

    @Override
    public SessionResponse registerSession(SessionRequest sessionRequest) throws CustomException {
        
        try {
            
            // 2️⃣ Buscar FK User
            User user = userRepository.findById(sessionRequest.getUserId()
            ).orElseThrow(() ->
                    new CustomException("User not found",
                            HttpStatus.NOT_FOUND)
            );

            // 3️⃣ Buscar FK SessionState
            SessionState sessionState = sessionStateRepository.findById(
                    sessionRequest.getSessionStateId()
            ).orElseThrow(() ->
                    new CustomException("SessionState not found",
                            HttpStatus.NOT_FOUND)
            );

            Session session = new Session();
            session.setStart_datetime(sessionRequest.getStart_datetime());
            
            session.setUser(user);
            session.setSessionState(sessionState);

            session = sessionRepository.save(session);

            SessionResponse sessionResponse = new SessionResponse();
            
            sessionResponse = sessionMapper.toDto(session);
            
            return sessionResponse;
            
        } catch(DataAccessException e){

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

    @Override
    public SessionResponse updateSession(Integer id, SessionRequest sessionRequest) throws CustomException {
        
        try {
            Optional<Session> sessionFinded = sessionRepository.findById(id);

            Session session = sessionFinded.get();

            session.setFinish_datetime(LocalDateTime.now().withNano(0));

            Session sessionSaved = sessionRepository.save(session);
            SessionResponse sessionResponse = sessionMapper.toDto(sessionSaved);
            
            return sessionResponse;

        } catch (DataAccessException e) {

            throw new CustomException(
                    env.getProperty("database.access-error") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {

            throw new CustomException(
                    env.getProperty("unhandled-exception") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
