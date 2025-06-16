package com.inmobiliaria.server.repositories.PropertyState;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inmobiliaria.server.models.PropertyState;

public interface PropertyStateRepository extends JpaRepository <PropertyState, Integer>{

    List<PropertyState> findAll();
    Optional<PropertyState> findById(int id);
    Optional<PropertyState> findByState(String state);
    PropertyState save(PropertyState propertyState);
    
}