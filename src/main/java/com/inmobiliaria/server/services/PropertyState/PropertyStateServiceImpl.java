package com.inmobiliaria.server.services.PropertyState;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
            
            Optional<PropertyState> propertyStateDatabase = propertyStateRepository.findById(propertyState.getId());
            
            if (propertyStateDatabase.isPresent()) {
                
                throw new CustomException(
                    env.getProperty("database.existing-data"), 
                    HttpStatus.CONFLICT
                );
            }
            
            PropertyState registredPropertyState = propertyStateRepository.save(propertyState);

            return registredPropertyState;

        } 
        catch (DataIntegrityViolationException e) {
            throw new CustomException(
                env.getProperty("database.data-integrity-violation")+": "+e.getMessage(), 
                HttpStatus.CONFLICT
            );
        }
        catch (DataAccessException e) {
            throw new CustomException(
                env.getProperty("data.access-error")+": "+e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        catch (Exception e) {
            throw new CustomException(
                env.getProperty("unhadled-error")+": "+e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    public List<PropertyState> getAllPropertyStates() throws CustomException{
        
        try {
            List<PropertyState> propertyStateList = propertyStateRepository.findAll();
            return propertyStateList;
        } 
        catch (DataAccessException e) {
            
            throw new CustomException(
                env.getProperty("data.access-error")+": "+e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        catch(Exception e){
                throw new CustomException(
                env.getProperty("unhandled-exception")+": "+e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    public PropertyState updatePropertyState(PropertyState propertyState) throws CustomException {
        
        try {
            Optional<PropertyState> propertyStateDatabase = propertyStateRepository.findById(propertyState.getId());
            
            if (propertyStateDatabase.isPresent()) {
                
                throw new CustomException(
                    env.getProperty("database.existing-data"), 
                    HttpStatus.CONFLICT
                );
            }

            PropertyState registredPropertyState = propertyStateRepository.save(propertyState);

            return registredPropertyState;

        } 
        catch (DataIntegrityViolationException e) {
            throw new CustomException(
                env.getProperty("database.data-integrity-violation")+": "+e.getMessage(), 
                HttpStatus.CONFLICT
            );
        }
        catch (DataAccessException e) {
            throw new CustomException(
                env.getProperty("data.access-error")+": "+e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        catch (Exception e) {
            throw new CustomException(
                env.getProperty("unhadled-error")+": "+e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    public void deletePropertyState(int id) throws CustomException {
        
        try {
            
            propertyStateRepository.deleteById(id);

        } 
        catch (DataIntegrityViolationException e) {
            throw new CustomException(
                env.getProperty("database.data-integrity-violation")+": "+e.getMessage(), 
                HttpStatus.CONFLICT
            );
        }
        catch (DataAccessException e) {
            throw new CustomException(
                env.getProperty("data.access-error")+": "+e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        catch (Exception e) {
            throw new CustomException(
                env.getProperty("unhadled-error")+": "+e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
