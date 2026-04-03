package com.inmobiliaria.server.models;

import com.inmobiliaria.server.dto.AgentState.AgentStateDataDto;

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
@Table(name = "agent",

        indexes = {
                @Index(name = "fk_agent_address1_idx", columnList = "address_id"),
                @Index(name = "fk_agent_agent_state1_idx", columnList = "agent_state_Id"),
                @Index(name = "key_agent_name", columnList = "name"),
                @Index(name = "key_agent_lastname", columnList = "lastname"),

        }, uniqueConstraints = {
                @UniqueConstraint(name = "unique_agent_identification_number", columnNames = "identification_number"),
                @UniqueConstraint(name = "email_unique", columnNames = "email"),
                @UniqueConstraint(name = "phone_number_unique", columnNames = "phone_number"),
                @UniqueConstraint(name = "agent_registration_unique", columnNames = "agent_registration"),
        })

public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

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

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "agent_registration", nullable = false, length = 60)
    private String agentRegistration;

    @Column(name = "profile_photo", nullable = false, length = 200)
    private String profilePhoto;

    @ManyToOne
    @JoinColumn(name = "agent_state_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_agent_agent_state1"))
    private AgentState agentState;

    @OneToOne
    @JoinColumn(name = "address_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_agent_address1"))
    private Address address;
}