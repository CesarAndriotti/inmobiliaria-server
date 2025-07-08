package com.inmobiliaria.server.services.CustomerType;

import java.util.List;

import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.CustomerType;

public interface CustomerTypeService {

    public CustomerType registerCustomerType(CustomerType customerType) throws CustomException;
    public List<CustomerType> getAllCustomerTypes() throws CustomException;    
    public CustomerType updateCustomerType(CustomerType customerType) throws CustomException;
    public void deleteCustomerType(Integer id) throws CustomException;
}
