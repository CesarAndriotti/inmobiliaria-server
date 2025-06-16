package com.inmobiliaria.server.services.PropertyType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.PropertyType;
import com.inmobiliaria.server.repositories.PropertyType.PropertyTypeRepository;

@Service
public class PropertyTypeServiceImpl {

    @Autowired
    Environment env;
    @Autowired
    PropertyTypeRepository propertyTypeRepository;

    public PropertyType registerPropertyType(PropertyType propertyType) throws CustomException {
        
        try {
            
            if (propertyType == null) {
                
                throw new CustomException(
                    env.getProperty("http.clien.bad-request"), 
                    HttpStatus.CONFLICT
                );
            }
            
            PropertyType registredPropertyType = propertyTypeRepository.save(propertyType);

            return registredPropertyType;
        } 
        catch(InternalServerError e){
            
            throw new CustomException(
                env.getProperty("http.server.internal-server"), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
