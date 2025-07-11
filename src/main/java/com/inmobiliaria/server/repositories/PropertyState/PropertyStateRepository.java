package com.inmobiliaria.server.repositories.PropertyState;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.inmobiliaria.server.models.PropertyState;

public interface PropertyStateRepository extends JpaRepository <PropertyState, Integer>{

    public List<PropertyState> findAll();
    public <S extends PropertyState> S save(S propertyState);
    public Optional<PropertyState> findById(int id);
    public Optional<PropertyState> findByState(String state);
    public void deleteById(int id);  
}