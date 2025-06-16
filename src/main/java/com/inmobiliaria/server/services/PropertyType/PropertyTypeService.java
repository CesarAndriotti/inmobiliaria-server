package com.inmobiliaria.server.services.PropertyType;

import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.PropertyType;

public interface PropertyTypeService {

    public PropertyType registerPropertyType(PropertyType propertytype) throws CustomException;
}
