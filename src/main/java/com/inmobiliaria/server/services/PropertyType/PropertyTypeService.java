package com.inmobiliaria.server.services.PropertyType;

import java.util.List;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.PropertyType;

public interface PropertyTypeService {

    public PropertyType registerPropertyType(PropertyType propertyType) throws CustomException;
    public List<PropertyType> getAllPropertyTypes() throws CustomException;
    public PropertyType updatePropertyType(PropertyType propertyType) throws CustomException;
    public void deletePropertyType(Integer id) throws CustomException;
}
