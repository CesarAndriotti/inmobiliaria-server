package com.inmobiliaria.server.models;

import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor  
@Entity
@Table(name = "Agent",

    indexes = {
        @Index(name = "fk_Agent_Address1_idx", columnList = "address_Id"),
        @Index(name = "fk_Agent_AgentState1_idx", columnList = "agent_State_Id")
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "unique_Agent_IdentificationNumber", columnNames = "identification_number"),
        @UniqueConstraint(name = "Email_UNIQUE", columnNames = "email"),
        @UniqueConstraint(name = "Phone_Number_UNIQUE", columnNames = "phone_number"),
        @UniqueConstraint(name = "Agent_Registration_UNIQUE", columnNames = "agent_registration"),
    }
)

public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false) 
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "lastname", nullable = false, length = 100)
    private String lastname;

    @Column(name = "identification_number", nullable = false, length = 60)
    private String identificationNumber;

    @Column(name = "date_of_birth", nullable = false)
    private java.sql.Date dateOfBirth;

    @Column(name = "phone_number", nullable = false, length = 60)
    private String phoneNumber;

    @Column(name = "email", nullable = false, length = 60)
    private String email;

    @Column(name = "agent_registration", nullable = false, length = 60)
    private String agentRegistration;

    @Column(name = "profile_photo", nullable = false, length = 200)
    private String profilePhoto;

    @ManyToOne
    @JoinColumn(name = "agent_State_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Agent_Agent_State1")) 
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AgentState agentState;

    @OneToOne
    @JoinColumn(name = "address_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Agent_Address1")) 
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Address address;  
    /* 
    @Override
    public boolean equals(Object o) {
        
        if (this == o) return true; 
        if (o == null || getClass() != o.getClass()) return false;

        Agent agent = (Agent) o;  
        
        return Objects.equals(id, agent.id) && 
            Objects.equals(name, agent.name) &&
            Objects.equals(lastname, agent.lastname) &&
            Objects.equals(identificationNumber, agent.identificationNumber) && 
            Objects.equals(dateOfBirth.toString(), agent.dateOfBirth.toString()) &&
            Objects.equals(phoneNumber, agent.phoneNumber) &&
            Objects.equals(email, agent.email) &&
            Objects.equals(agentRegistration, agent.agentRegistration) &&
            Objects.equals(profilePhoto, agent.profilePhoto) &&
            Objects.equals(agentState, agent.agentState) &&
            Objects.equals(address, agent.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, identificationNumber, dateOfBirth, phoneNumber, email, agentRegistration, profilePhoto, agentState, address);
    }
        */
}