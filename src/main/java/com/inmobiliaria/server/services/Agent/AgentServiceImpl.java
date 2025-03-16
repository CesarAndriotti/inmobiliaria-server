package com.inmobiliaria.server.services.Agent;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.dto.UserDto;
import com.inmobiliaria.server.models.Agent;
import com.inmobiliaria.server.models.UserType;
import com.inmobiliaria.server.repositories.Address.AddressRepository;
import com.inmobiliaria.server.repositories.Agent.AgentRepository;
import com.inmobiliaria.server.services.User.UserServiceImpl;
import jakarta.transaction.Transactional;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    @Transactional
    public Optional<UserDto> registerAgentAndUser(UserDto userDto) {
        
        if (userDto == null || userDto.getAgent() == null) {
            return Optional.empty();
        }

        try {
            
            addressRepository.save(userDto.getAgent().getAddress());
            Agent firstAgent = userDto.getAgent();
            Agent savedAgent = agentRepository.save(firstAgent);
            UserType userType = new UserType();
            userType.setId(1);
            userDto.setUser_type(userType);
            userDto.setAgent(savedAgent);
            UserDto newUserDto = userServiceImpl.registerUser(userDto);

            return Optional.of(newUserDto);

        } catch (Exception e) {
            
            return Optional.empty();
        }
    }
}


