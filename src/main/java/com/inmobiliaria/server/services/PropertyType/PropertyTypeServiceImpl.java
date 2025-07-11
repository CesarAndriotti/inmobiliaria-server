package com.inmobiliaria.server.services.PropertyType;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.PropertyType;
import com.inmobiliaria.server.repositories.PropertyType.PropertyTypeRepository;

@Service
public class PropertyTypeServiceImpl implements PropertyTypeService{

    @Autowired
    Environment env;
    @Autowired
    PropertyTypeRepository propertyTypeRepository;

    public PropertyType registerPropertyType(PropertyType propertyType) throws CustomException {
        
        try {
            
            Optional<PropertyType> propertyTypeDatabase = propertyTypeRepository.findByType(propertyType.getType());

            if (propertyTypeDatabase.isPresent()) {
                throw new CustomException(
                    env.getProperty("database.existing-data"), 
                    HttpStatus.CONFLICT
                );
            }
            
            PropertyType registredPropertyType = propertyTypeRepository.save(propertyType);

            return registredPropertyType;
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
    public List<PropertyType> getAllPropertyTypes() throws CustomException{
        
        try {
            List<PropertyType> propertyTypeList = propertyTypeRepository.findAll();
            return propertyTypeList;
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
    public PropertyType updatePropertyType(PropertyType propertyType) throws CustomException {
        
        try {
            Optional<PropertyType> propertyTypeDatabase = propertyTypeRepository.findById(propertyType.getId());

            if (propertyTypeDatabase.isPresent()) {
                throw new CustomException(
                    env.getProperty("database.existing-data"), 
                    HttpStatus.CONFLICT
                );
            }

            PropertyType updatedPropertyType = propertyTypeRepository.save(propertyType);
            return updatedPropertyType;

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
    public void deletePropertyType(Integer id) throws CustomException {
        
        try {
            
            propertyTypeRepository.deleteById(id);

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
