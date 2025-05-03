package com.inmobiliaria.server.services.CustomerType;

import java.util.List;

import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.CustomerType;

public interface CustomerTypeService {

    public List<CustomerType> showCustomerTypeList() throws CustomException;
    public CustomerType getCustomerTypeById(Integer id) throws CustomException;
    public CustomerType registerCustomerType(CustomerType customerType) throws CustomException;

    public CustomerType updateCustomerType(CustomerType customerType) throws CustomException;

    public Integer deleteCustomerType(Integer id) throws CustomException;
}
