package com.inmobiliaria.server.services.Customer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.Address;
import com.inmobiliaria.server.models.Agent;
import com.inmobiliaria.server.models.Customer;
import com.inmobiliaria.server.repositories.Address.AddressRepository;
import com.inmobiliaria.server.repositories.Customer.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    Environment env;

    @Override
    public Customer registerCustomer(Customer customer) throws CustomException {
        
        if (customerRepository.findByIdentificationNumberOrPhoneNumberOrEmail(customer.getIdentificationNumber(), customer.getPhoneNumber(), customer.getEmail()).isPresent()) {
            throw new CustomException("The customer exists in the database", HttpStatus.CONFLICT);
        }

        Address address = customer.getAddress();
        Optional <Address> existingAddress = addressRepository.findByStreetnameAndNumber(address.getStreetName(), address.getNumber());
        
        if (existingAddress.isPresent()) customer.setAddress(existingAddress.get());
        else addressRepository.save(address);

        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) throws CustomException {
        
        try {
            Optional<Customer> customerDatabase = customerRepository.findById(customer.getId());

            if (!customer.equals(customerDatabase.get()) || !customer.getAddress().equals(customerDatabase.get().getAddress())) {
                addressRepository.save(customer.getAddress());
                Customer customerUpdated = customerRepository.save(customer);

                return customerUpdated;
            }
            else{
                throw new CustomException("Identical Data", HttpStatus.CONFLICT);
            }
        }
        catch(InternalServerError e){
            throw new CustomException(env.getProperty("http.server.internal-server"), HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }
}
