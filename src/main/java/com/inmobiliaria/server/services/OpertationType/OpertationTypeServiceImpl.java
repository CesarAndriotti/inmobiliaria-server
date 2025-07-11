package com.inmobiliaria.server.services.OpertationType;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.OperationType;
import com.inmobiliaria.server.repositories.OperationType.OperationTypeRepository;

@Service
public class OpertationTypeServiceImpl implements OpertationTypeService{

    @Autowired
    Environment env;
    @Autowired
    OperationTypeRepository operationTypeRepository;

    @Override
    public List<OperationType> getAllOpertationTypes() throws CustomException {
        
        try {
            List<OperationType> operationTypeList = operationTypeRepository.findAll();
            return operationTypeList;
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
    public OperationType registerOperationType(OperationType operationType) throws CustomException {
        
        try {

            Optional<OperationType> operationDatabase = operationTypeRepository.findByType(operationType.getType());

            if (operationDatabase.isPresent()) {
                
                throw new CustomException(
                    env.getProperty("database.existing-data"), 
                    HttpStatus.CONFLICT
                );
            }

            OperationType operationTypeRegistered = operationTypeRepository.save(operationType);

            return operationTypeRegistered;
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
    public OperationType updateOperationType(OperationType operationType) throws CustomException {
        
        try {

            Optional<OperationType> operationDatabase = operationTypeRepository.findById(operationType.getId());

            if (operationDatabase.isPresent()) {
                
                throw new CustomException(
                    env.getProperty("database.existing-data"), 
                    HttpStatus.CONFLICT
                );
            }

            OperationType updatedOperationType = operationTypeRepository.save(operationType);
            return updatedOperationType;

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
    public void deleteOperationType(Integer id) throws CustomException {
        
        try {
            
            operationTypeRepository.deleteById(id);
            
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
