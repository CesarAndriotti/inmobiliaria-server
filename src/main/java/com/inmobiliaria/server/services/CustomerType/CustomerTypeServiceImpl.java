package com.inmobiliaria.server.services.CustomerType;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.repositories.CustomerType.CustomerTypeRepository;
import com.inmobiliaria.server.models.CustomerType;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService{

    @Autowired
    CustomerTypeRepository customerTypeRepository;
    
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
    public CustomerType registerCustomerType(CustomerTypeService customerType) throws CustomException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registerCustomerType'");
    }

    @Override
    public CustomerType updateCustomerType(CustomerTypeService customerType) throws CustomException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCustomerType'");
    }

    @Override
    public CustomerType deleteCustomerType(Integer id) throws CustomException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCustomerType'");
    }
}
