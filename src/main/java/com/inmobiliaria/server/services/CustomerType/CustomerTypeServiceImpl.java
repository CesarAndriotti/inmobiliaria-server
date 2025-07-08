package com.inmobiliaria.server.services.CustomerType;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.repositories.CustomerType.CustomerTypeRepository;
import com.inmobiliaria.server.models.CustomerType;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService{

    @Autowired
    CustomerTypeRepository customerTypeRepository;
    @Autowired
    Environment env;
    
    @Override
    public List<CustomerType> getAllCustomerTypes() throws CustomException{
        
        try {
            List<CustomerType> customerTypeList = customerTypeRepository.findAll();
            return customerTypeList;
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
    public CustomerType registerCustomerType(CustomerType customerType) throws CustomException {
        
        try {
            Optional<CustomerType> customerTypeDatabase = customerTypeRepository.findById(customerType.getId());

            if (customerTypeDatabase.isPresent()) {
                
                throw new CustomException(
                    env.getProperty("database.existing-data"), 
                    HttpStatus.CONFLICT
                );
            }

            CustomerType customerTypeSaved = customerTypeRepository.save(customerType);
            return customerTypeSaved;
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
    public CustomerType updateCustomerType(CustomerType customerType) throws CustomException {
        
        try {

            Optional<CustomerType> customerTypeDatabase = customerTypeRepository.findById(customerType.getId());

            if(customerTypeDatabase.isPresent()){

                throw new CustomException(
                    env.getProperty("database.existing-data"), 
                    HttpStatus.CONFLICT
                );
            }

            CustomerType updatedCustomerType = customerTypeRepository.save(customerType);
            return updatedCustomerType;

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
    public void deleteCustomerType(Integer id) throws CustomException {
        
        try {
            customerTypeRepository.deleteById(id);
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
