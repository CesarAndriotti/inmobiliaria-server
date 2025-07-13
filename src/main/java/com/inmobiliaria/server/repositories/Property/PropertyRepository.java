package com.inmobiliaria.server.repositories.Property;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.inmobiliaria.server.models.Property;

public interface PropertyRepository extends JpaRepository <Property, Integer>{
    
    public List<Property> findAll();
    //Optional<Property> findByIdentificationNumberOrEmailOrAgentRegistrationOrPhoneNumber(String identificationNumber, String Email, String agentRegistration, String phoneNumber);
    <S extends Property> S save(S agent); 
}
