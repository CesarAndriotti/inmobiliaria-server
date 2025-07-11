package com.inmobiliaria.server.services.Address;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.Address;
import com.inmobiliaria.server.repositories.Address.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    Environment env;

    @Override
    public Optional<Address> getAddressByStreetnameAndNumber(Address address) throws CustomException {
        
        try {
            Optional<Address> addressDatabase = addressRepository.findByStreetnameAndNumber(address.getStreetName(), address.getNumber());
            
            return addressDatabase;
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
    public Address saveAddress(Address address) throws CustomException {
        
        return addressRepository.save(address);
    }

    
}
