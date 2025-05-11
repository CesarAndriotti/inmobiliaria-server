package com.inmobiliaria.server.services.PropertyState;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.PropertyState;
import com.inmobiliaria.server.repositories.PropertyState.PropertyStateRepository;

@Service
public class PropertyStateServiceImpl implements PropertyStateService {

    @Autowired
    PropertyStateRepository propertyStateRepository;
    @Autowired
    Environment env;
    
    @Override
    public PropertyState registerPropertyState(PropertyState propertyState) throws CustomException {
        
        try {
            
            if (propertyStateRepository.findByState(propertyState.getState()).isPresent()) {
                
                throw new CustomException(
                    env.getProperty("database.existing-data"), 
                    HttpStatus.CONFLICT
                );
            }
            
            PropertyState registredPropertyState = propertyStateRepository.save(propertyState);

            return registredPropertyState;

        } 
        catch(InternalServerError e){
            
            throw new CustomException(
                env.getProperty("http.server.internal-server"), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        } 
    }

    @Override
    public List<PropertyState> searchPropertyStates() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchPropertyStates'");
    }

    @Override
    public PropertyState updatePropertyState(PropertyState PropertyState) throws CustomException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePropertyState'");
    }

    @Override
    public PropertyState deletePropertyState(PropertyState PropertyState) throws CustomException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePropertyState'");
    }
    
    
}
