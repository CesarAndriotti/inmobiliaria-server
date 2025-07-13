package com.inmobiliaria.server.services.Property;

import java.util.List;

import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.Property;

public interface PropertyService {
    
    public List<Property> getAllProperties() throws CustomException;
    public Property registerProperty(Property property) throws CustomException;
    public Property updateProperty(Property property) throws CustomException;
}
