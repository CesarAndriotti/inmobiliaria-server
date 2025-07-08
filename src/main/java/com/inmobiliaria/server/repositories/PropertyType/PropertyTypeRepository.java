package com.inmobiliaria.server.repositories.PropertyType;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.inmobiliaria.server.models.PropertyType;

public interface PropertyTypeRepository extends CrudRepository <PropertyType, Integer>{
    
    public List<PropertyType> findAll();
    public <S extends PropertyType> S save(S propertyType);
    public Optional<PropertyType> findById(Integer id);
    public void deleteById(int id);
}
