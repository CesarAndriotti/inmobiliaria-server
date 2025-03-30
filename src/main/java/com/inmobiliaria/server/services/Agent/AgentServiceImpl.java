package com.inmobiliaria.server.services.Agent;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.dto.AgentDto;
import com.inmobiliaria.server.dto.UserDto;
import com.inmobiliaria.server.models.Agent;
import com.inmobiliaria.server.models.Address;
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

    private final AgentMapper agentMapper = AgentMapper.INSTANCE;

    @Override
    @Transactional
    public Optional<UserDto> registerAgentAndUser(UserDto userDto) {
        
        //Analizar cuales controllers son necesarios y cuales no
         
        if (userDto == null || userDto.getAgent() == null) {
            return Optional.empty();
        }

        try {
            
            Address newAddress = addressRepository.save(userDto.getAgent().getAddress());
            
            Agent agentToRegister = userDto.getAgent();
            agentToRegister.setAddress(newAddress);
            Agent newAgent = agentRepository.save(agentToRegister);
            
            UserType userType = new UserType();
            userType.setId(1);
            userDto.setUser_type(userType);
            userDto.setAgent(newAgent);
            UserDto newUserDto = userServiceImpl.registerUser(userDto);

            return Optional.of(newUserDto);

        } catch (Exception e) {
            
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<AgentDto> updateAgentData(AgentDto agentDto){

        Agent agentToModify = agentMapper.ToAgent(agentDto);          
        Address addressToModify = agentToModify.getAddress(); 
        AgentDto newAgentDto = null;

        try {

            Optional <Agent> agentDataBase = agentRepository.findById(agentToModify.getId());
            System.out.println(agentToModify.toString()+"\n"+agentDataBase.get().toString());
            if (!agentToModify.toString().equals(agentDataBase.get().toString())) {
                if (!addressToModify.toString().equals(agentDataBase.get().getAddress().toString())) {
                    agentToModify.setAddress(addressToModify);
                    addressRepository.save(addressToModify);
                }
                Agent agentUpdated = agentRepository.save(agentToModify);
                newAgentDto = agentMapper.ToAgentDto(agentUpdated);
            }
            else{
                newAgentDto = agentDto;
            }
            
            return Optional.of(newAgentDto);

        } catch (Exception e) {
            
            return Optional.empty();
        }          
    }
}


