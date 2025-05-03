package com.inmobiliaria.server.services.CustomerType;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
    public List<CustomerType> showCustomerTypeList() throws CustomException{
        
        List<CustomerType> customerTypeList = customerTypeRepository.findAll();
        return customerTypeList;
    }

    @Override
    public CustomerType getCustomerTypeById(Integer id) throws CustomException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCustomerTypeById'");
    }

    @Override
    public CustomerType registerCustomerType(CustomerType customerType) throws CustomException {
        
        CustomerType customerTypeSaved = customerTypeRepository.save(customerType);

        return customerTypeSaved;
    }

    @Override
    public CustomerType updateCustomerType(CustomerType customerType) throws CustomException {
        
        try {
            CustomerType updatedCustomerType = customerTypeRepository.save(customerType);
            return updatedCustomerType;
        } catch (Exception e) {
            throw new CustomException(env.getProperty("database.entity-not-found"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Integer deleteCustomerType(Integer id) throws CustomException {
        
        try {
            customerTypeRepository.deleteById(id);
            return id;
        } catch (Exception e) {
            throw new CustomException(env.getProperty("database.entity-not-found"), HttpStatus.NOT_FOUND);
        }
    }
}
