package com.inmobiliaria.server.services.Customer;

import java.util.List;
import com.inmobiliaria.server.dto.Customer.CustomerRequest;
import com.inmobiliaria.server.dto.Customer.CustomerResponse;
import com.inmobiliaria.server.exceptions.CustomException;

public interface CustomerService {

    public List<CustomerResponse> getAllCustomers() throws CustomException;
    public CustomerResponse registerCustomer(CustomerRequest customerRequest) throws CustomException;
}
