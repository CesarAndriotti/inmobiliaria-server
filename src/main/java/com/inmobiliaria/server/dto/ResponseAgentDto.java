package com.inmobiliaria.server.dto;

import com.inmobiliaria.server.models.Agent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  
@NoArgsConstructor 
public class ResponseAgentDto {
    
    private int id;
    private String name;
    private String lastname;
    private String identificationNumber;
    private java.sql.Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private String agentRegistration;
    private String profilePhoto;
    private AgentStateDto agentState;
    private AddressDto address;

    public ResponseAgentDto(Agent agent) {

        this.id = agent.getId();
        this.name = agent.getName();
        this.lastname = agent.getLastname();
        this.identificationNumber = agent.getIdentificationNumber();
        this.dateOfBirth = agent.getDateOfBirth();
        this.phoneNumber = agent.getPhoneNumber();
        this.email = agent.getEmail();
        this.agentRegistration = agent.getAgentRegistration();
        this.profilePhoto = agent.getProfilePhoto();

        this.agentState = new AgentStateDto();
        this.agentState.setId(agent.getAgentState().getId());
        this.agentState.setState(agent.getAgentState().getState());
        this.address = new AddressDto(agent.getAddress());  
    }
}