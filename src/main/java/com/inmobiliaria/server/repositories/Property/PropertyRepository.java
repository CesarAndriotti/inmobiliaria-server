package com.inmobiliaria.server.repositories.Property;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.inmobiliaria.server.models.Property;

public interface PropertyRepository extends CrudRepository <Property, Integer>{
    
    public List<Property> findAll();
}
