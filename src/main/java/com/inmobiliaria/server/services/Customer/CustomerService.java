package com.inmobiliaria.server.services.Customer;

import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.Customer;

public interface CustomerService {

    public Customer registerCustomer(Customer customer) throws CustomException;
    public Customer updateCustomer(Customer customer) throws CustomException;
}
