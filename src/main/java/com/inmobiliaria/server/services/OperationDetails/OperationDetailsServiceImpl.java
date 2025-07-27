package com.inmobiliaria.server.services.OperationDetails;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.OperationDetails;
import com.inmobiliaria.server.repositories.OperationDetails.OperationDetailsRepository;

@Service
public class OperationDetailsServiceImpl implements OperationDetailsService {

    @Autowired
    OperationDetailsRepository operationDetailsRepository;
    @Autowired
    Environment env;

    @Override
    public OperationDetails addLinkAsOperationDetails(OperationDetails operationDetails) throws CustomException {
        
        try {
            Optional<OperationDetails> operationDetailsDatabase = operationDetailsRepository.findByOperationIdAndCustomerId(operationDetails.getOperation().getId(), operationDetails.getCustomer().getId());
            
            if (operationDetailsDatabase.isPresent()) {
                throw new CustomException(
                    env.getProperty("database.identical-data"),
                    HttpStatus.CONFLICT
                );
            }

            return operationDetailsRepository.save(operationDetails);
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
    public OperationDetails updateOperationDetails(OperationDetails operationDetails) throws CustomException {
        
        try {
            Optional<OperationDetails> operationDetailsDatabase = operationDetailsRepository.findByOperationIdAndCustomerId(operationDetails.getOperation().getId(), operationDetails.getCustomer().getId());
            
            if (operationDetailsDatabase.isPresent()) {
                
                if (operationDetailsDatabase.equals(operationDetails)) {
                    
                    throw new CustomException(
                        env.getProperty("database.identical-data"),
                        HttpStatus.CONFLICT
                    );
                }
            }

            return operationDetailsRepository.save(operationDetails);
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
