package com.inmobiliaria.server.repositories.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inmobiliaria.server.models.Property;

public interface PropertyRepository extends JpaRepository<Property, Integer> {

    
}
