package com.inmobiliaria.server.services.Customer;

import java.util.List;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.Customer;

public interface CustomerService {

    public List<Customer> getAllCustomers() throws CustomException;
    public Customer registerCustomer(Customer customer) throws CustomException;
    public Customer updateCustomer(Customer customer) throws CustomException;
}
