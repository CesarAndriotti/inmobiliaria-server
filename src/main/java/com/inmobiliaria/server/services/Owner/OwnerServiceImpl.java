package com.inmobiliaria.server.services.Owner;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.Owner;
import com.inmobiliaria.server.repositories.Owner.OwnerRepository;

@Service
public class OwnerServiceImpl implements OwnerService{
    
    @Autowired
    Environment env;
    @Autowired 
    OwnerRepository ownerRepository;

    @Override
    public Owner addLinkAsOwner(Owner owner) throws CustomException {
        
        try {

            Optional<Owner> ownerDatabase = ownerRepository.findByCustomerIdAndPropertyId(owner.getCustomer().getId(), owner.getProperty().getId());

            if (ownerDatabase.isPresent()) {
                throw new CustomException(
                    env.getProperty("database.identical-data"),
                    HttpStatus.CONFLICT
                );
            }

            return ownerRepository.save(owner);
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
    public Owner updateOwner(Owner owner) throws CustomException{

        try{
            Optional<Owner> ownerDatabase = ownerRepository.findByCustomerIdAndPropertyId(owner.getCustomer().getId(), owner.getProperty().getId());

            if (ownerDatabase.isPresent()) {
                
                if (ownerDatabase.equals(owner)) {
                    
                    throw new CustomException(
                        env.getProperty("database.identical-data"),
                        HttpStatus.CONFLICT
                    );
                }
            }

            return ownerRepository.save(owner);
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
