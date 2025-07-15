package com.inmobiliaria.server.services.Property;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.Address;
import com.inmobiliaria.server.models.Property;
import com.inmobiliaria.server.repositories.Property.PropertyRepository;
import com.inmobiliaria.server.services.Address.AddressServiceImpl;
import jakarta.transaction.Transactional;

@Service
public class PropertyServiceImpl implements PropertyService{

    @Autowired
    PropertyRepository propertyRepository;
    @Autowired
    AddressServiceImpl addressServiceImpl;
    @Autowired 
    Environment env;

    @Override
    public List<Property> getAllProperties() throws CustomException {
        
        try {
            List<Property> propertyList = propertyRepository.findAll();
            return propertyList;
        } 
        catch(DataAccessException e){

            throw new CustomException(
                env.getProperty("database.access-error")+": "+e.getMessage(), 
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
    @Transactional
    public Property registerProperty(Property property) throws CustomException {
        
        try {
            Address address = property.getAddress();
            Optional <Address> addressDatabase = addressServiceImpl.getAddressByStreetnameAndNumber(address);
            
            if (addressDatabase.isPresent()) {
                throw new CustomException(
                    env.getProperty("database.existing-data") + " Data: " + address.getStreetName()+", "+address.getNumber(),
                    HttpStatus.CONFLICT
                );
            }

            addressServiceImpl.saveAddress(address);
            return propertyRepository.save(property);
        } 
        catch (DataIntegrityViolationException e) { 
            throw new CustomException(
                env.getProperty("database.data-integrity-violation")+": "+e.getMessage(), 
                HttpStatus.CONFLICT);
        }
        catch (DataAccessException e) {
            throw new CustomException(
                env.getProperty("data.access-error")+": "+e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
         catch (Exception e) {
            throw new CustomException(
                env.getProperty("unhadled-error")+": "+e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Property updateProperty(Property property) throws CustomException {
        
        try {
            
            Optional<Property> propertyDatabase = propertyRepository.findById(property.getId());
            
            if (propertyDatabase.isEmpty()) {
                throw new CustomException(
                    env.getProperty("database.entity-not-found"),
                    HttpStatus.NOT_FOUND
                );
            }

            Property existingProperty = propertyDatabase.get();

            boolean isSameProperty = property.equals(existingProperty);

            if (isSameProperty) {
                
                throw new CustomException(
                    env.getProperty("database.identical-data"),
                    HttpStatus.CONFLICT
                );
            }

            Address address = property.getAddress();
            Optional <Address> addressDatabase = addressServiceImpl.getAddressByStreetnameAndNumber(address);

            if (addressDatabase.isPresent()) property.setAddress(addressDatabase.get());
            else addressServiceImpl.saveAddress(address);

            return propertyRepository.save(property);
        } 
        catch (DataIntegrityViolationException e) {
            throw new CustomException(
                env.getProperty("database.data-integrity-violation")+": "+e.getMessage(), 
                HttpStatus.CONFLICT);
        }
        catch (DataAccessException e) {
            throw new CustomException(
                env.getProperty("data.access-error")+": "+e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
         catch (Exception e) {
            throw new CustomException(
                env.getProperty("unhadled-error")+": "+e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
