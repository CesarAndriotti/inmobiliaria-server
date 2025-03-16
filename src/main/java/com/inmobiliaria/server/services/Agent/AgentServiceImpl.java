package com.inmobiliaria.server.services.Agent;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.dto.AgentDto;
import com.inmobiliaria.server.dto.ResponseAgentDto;
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
            // Guardar la dirección asociada al agente
            addressRepository.save(userDto.getAgent().getAddress());

            // Guardar el agente
            Agent firstAgent = userDto.getAgent();
            Agent savedAgent = agentRepository.save(firstAgent);

            // Asignar tipo de usuario antes de guardarlo
            UserType userType = new UserType();
            userType.setId(1);
            userDto.setUser_type(userType);
            userDto.setAgent(savedAgent);

            // Registrar el usuario después de haber guardado el agente
            UserDto newUserDto = userServiceImpl.registerUser(userDto);

            return Optional.of(newUserDto);
        } catch (Exception e) {
            // Loggear la excepción para diagnóstico
            System.err.println("Error registering agent and user: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Agent> createAgentList() {
        
        List<Agent> agentList = agentRepository.findAll();
        return agentList;
    }

    @Transactional
    public ResponseEntity<ResponseAgentDto> register(AgentDto agentDto){

        //Address address = objectMapper.convertValue(agentDto.getAddress(), Address.class);
        //AgentState agentState = objectMapper.convertValue(agentDto.getAgentState(), AgentState.class);
        
        //Address savedAddress = addressRepository.insert(address);

        /*
        try{

            Agent agent = new Agent(

                0,
                agentDto.getName(),
                agentDto.getLastname(),
                agentDto.getIdentificationNumber(),
                agentDto.getDateOfBirth(),
                agentDto.getPhoneNumber(),
                agentDto.getEmail(),
                agentDto.getAgentRegistration(),
                agentDto.getProfilePhoto(),
                null,
                null //savedAddress
            );

            ResponseAgentDto responseAgentDto = agentMapper.AgentToResponseAgentDto(agentRepository.save(agent));
            
            return ResponseEntity.ok(responseAgentDto);

        }
        catch (IllegalArgumentException e) {
            
            return ResponseEntity.badRequest().body(null);

        } catch (Exception e) {
           
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        */

        return null;
    }

    @Override
    public Optional <Agent> getByIdentificationNumber(String identificationNumber) {
        
        //return agentRepository.findByIdentificationNumber(identificationNumber);

        return null;

    }

    @Override
    public List <Agent> getByLastname(String lastname) {
      
        //return agentRepository.findByLastname(lastname);

        return null;
    }

    @Override
    public Optional <Agent> getByEmail(String email) {
        
        //return agentRepository.findByEmail(email);

        return null;
    }
}


