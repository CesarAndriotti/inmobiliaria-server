package com.inmobiliaria.server.services.Property;

import java.util.List;

import com.inmobiliaria.server.dto.Property.PropertyRequest;
import com.inmobiliaria.server.dto.Property.PropertyResponse;
import com.inmobiliaria.server.exceptions.CustomException;

public interface PropertyService {

    public List<PropertyResponse> getAllProperties() throws CustomException;
    public PropertyResponse registerProperty(PropertyRequest propertyRequest) throws CustomException;
}
