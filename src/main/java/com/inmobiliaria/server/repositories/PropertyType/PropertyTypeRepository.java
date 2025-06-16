package com.inmobiliaria.server.repositories.PropertyType;

import org.springframework.data.repository.CrudRepository;

import com.inmobiliaria.server.models.PropertyType;

public interface PropertyTypeRepository extends CrudRepository <PropertyType, Integer>{
    
    <S extends PropertyType> S save(S propertyType);
}
