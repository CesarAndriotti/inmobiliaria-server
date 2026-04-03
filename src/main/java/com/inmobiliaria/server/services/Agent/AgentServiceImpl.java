package com.inmobiliaria.server.services.Agent;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.models.Address;
import com.inmobiliaria.server.models.Agent;
import com.inmobiliaria.server.dto.Address.AddressDataDto;
import com.inmobiliaria.server.dto.Agent.AgentDetailDataDto;
import com.inmobiliaria.server.models.AgentState;
import com.inmobiliaria.server.models.City;
import com.inmobiliaria.server.models.User;
import com.inmobiliaria.server.models.UserType;
import com.inmobiliaria.server.dto.Agent.AgentPublicDataDto;
import com.inmobiliaria.server.dto.Agent.AgentRequest;
import com.inmobiliaria.server.dto.Agent.AgentResponse;
import com.inmobiliaria.server.dto.AgentState.AgentStateDataDto;
import com.inmobiliaria.server.dto.User.UserResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.mappers.AddressMapper;
import com.inmobiliaria.server.mappers.AgentMapper;
import com.inmobiliaria.server.repositories.Address.AddressRepository;
import com.inmobiliaria.server.repositories.Agent.AgentRepository;
import com.inmobiliaria.server.repositories.User.UserRepository;
import com.inmobiliaria.server.services.Address.AddressServiceImpl;
import com.inmobiliaria.server.services.User.UserServiceImpl;
import jakarta.transaction.Transactional;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private Environment env;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressServiceImpl addressServiceImpl;
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AgentMapper agentMapper;
    @Autowired
    AddressMapper addressMapper;

    @Override
    public List<AgentResponse> getAllAgents() throws CustomException {

        try {
            List<AgentResponse> agents = agentMapper.toDtoList(agentRepository.findAll());
            return agents;
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

    @Override
    @Transactional
    public AgentResponse registerAgentAndUser(AgentRequest agentRequest) throws CustomException {

        try {

            // Consulto si ese agente existe, ya que es el actor principal
            Optional<Agent> agentDatabase = agentRepository
                    .findByIdentificationNumberOrEmailOrAgentRegistrationOrPhoneNumber(
                            agentRequest.getIdentificationNumber(),
                            agentRequest.getEmail(),
                            agentRequest.getAgentRegistration(),
                            agentRequest.getPhoneNumber());

            // Si esta presente, lanzo una excepcion
            if (agentDatabase.isPresent()) {
                throw new CustomException(
                        env.getProperty("database.existing-data") + " Data: " +
                                agentRequest.getIdentificationNumber() + ", " +
                                agentRequest.getEmail() + ", " +
                                agentRequest.getAgentRegistration() + ", " +
                                agentRequest.getPhoneNumber(),
                        HttpStatus.CONFLICT);
            }

            // Si no esta presente, consulto si el nick del usuario existe, ya que es un
            // dato unico
            List<UserResponse> userList = userServiceImpl.getAllUsers();

            boolean nickExists = userList.stream()
                    .anyMatch(u -> u.getNick()
                            .equals(agentRequest.getNick()));

            // Si el nick existe, lanzo una excepcion
            if (nickExists) {
                throw new CustomException(
                        env.getProperty("database.existing-data") + " Data: " +
                                agentRequest.getNick(),
                        HttpStatus.CONFLICT);
            }

            Optional<Address> addressDatabase = addressServiceImpl.getAddressByStreetnameAndNumber(
                    agentRequest.getAddressStreetName(), agentRequest.getAddressNumber());

            Address address = new Address();
            AgentDetailDataDto agentDetailDataDto = new AgentDetailDataDto();
            agentDetailDataDto.setName(agentRequest.getName());
            agentDetailDataDto.setLastname(agentRequest.getLastname());
            agentDetailDataDto.setDateOfBirth(agentRequest.getDateOfBirth());
            agentDetailDataDto.setIdentificationNumber(agentRequest.getIdentificationNumber());
            agentDetailDataDto.setEmail(agentRequest.getEmail());
            agentDetailDataDto.setPhoneNumber(agentRequest.getPhoneNumber());
            agentDetailDataDto.setAgentRegistration(agentRequest.getAgentRegistration());
            agentDetailDataDto.setProfilePhoto(agentRequest.getProfilePhoto());

            AgentStateDataDto agentState = new AgentStateDataDto();
            agentState.setId(agentRequest.getAgentStateId());
            agentDetailDataDto.setAgentState(agentState);

            if (addressDatabase.isPresent())
                address = addressDatabase.get();
            else {
                address.setStreetName(agentRequest.getAddressStreetName());
                address.setNumber(agentRequest.getAddressNumber());
                City city = new City();
                city.setId(agentRequest.getCityId());
                address.setCity(city);
                addressRepository.save(address);
            }

            AddressDataDto addressDataDto = addressMapper.entityToAddressData(address);
            agentDetailDataDto.setAddress(addressDataDto);

            User user = new User();
            user.setNick(agentRequest.getNick());
            user.setPassword(passwordEncoder.encode(agentRequest.getPassword()));

            UserType userType = new UserType();
            userType.setId(agentRequest.getUserTypeId());
            user.setUserType(userType);

            Agent agent = agentMapper.toEntity(agentRequest);

            user.setAgent(agent);

            //agentRepository.save(agent);
            userRepository.save(user);

            //AgentResponse agentResponse = agentMapper.toDto(agent);

            return null;

        } catch (DataIntegrityViolationException e) {
            throw new CustomException(
                    env.getProperty("database.data-integrity-violation") + ": " + e.getMessage(),
                    HttpStatus.CONFLICT);
        } catch (DataAccessException e) {
            throw new CustomException(
                    env.getProperty("data.access-error") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new CustomException(
                    env.getProperty("unhadled-error") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // EntityNotFoundException para una busqueda con id

    /*
     * @Override
     * 
     * @Transactional
     * public AgentResponse updateAgent(AgentResquest agentRequest) throws
     * CustomException{
     * 
     * try {
     * Optional <Agent> agentDataBase = agentRepository.findById(agent.getId());
     * 
     * if (agentDataBase.isEmpty()) {
     * throw new CustomException(
     * env.getProperty("database.entity-not-found"),
     * HttpStatus.NOT_FOUND
     * );
     * }
     * 
     * Agent existingAgent = agentDataBase.get();
     * 
     * boolean isSameAgent = agent.equals(existingAgent);
     * boolean isSameAddress =
     * agent.getAddress().equals(existingAgent.getAddress());
     * 
     * if (isSameAgent || isSameAddress) {
     * 
     * throw new CustomException(
     * env.getProperty("database.identical-data"),
     * HttpStatus.CONFLICT
     * );
     * }
     * 
     * addressServiceImpl.saveAddress(agent.getAddress());
     * Agent agentUpdated = agentRepository.save(agent);
     * 
     * return agentUpdated;
     * }
     * catch (DataIntegrityViolationException e) {
     * throw new CustomException(
     * env.getProperty("database.data-integrity-violation")+": "+e.getMessage(),
     * HttpStatus.CONFLICT);
     * }
     * catch (DataAccessException e) {
     * throw new CustomException(
     * env.getProperty("data.access-error")+": "+e.getMessage(),
     * HttpStatus.INTERNAL_SERVER_ERROR);
     * }
     * catch (Exception e) {
     * throw new CustomException(
     * env.getProperty("unhadled-error")+": "+e.getMessage(),
     * HttpStatus.INTERNAL_SERVER_ERROR);
     * }
     * }
     */

}
