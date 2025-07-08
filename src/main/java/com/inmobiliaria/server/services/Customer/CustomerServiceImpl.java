package com.inmobiliaria.server.services.Customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.Address;
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

        try {

            if (customerRepository.findByIdentificationNumberOrPhoneNumberOrEmail(customer.getIdentificationNumber(), customer.getPhoneNumber(), customer.getEmail()).isPresent()) {
                throw new CustomException(env.getProperty("data.identical-data"), HttpStatus.CONFLICT);
            }

            Address address = customer.getAddress();
            
            Optional <Address> existingAddress = addressRepository.findByStreetnameAndNumber(address.getStreetName(), address.getNumber());
        
            if (existingAddress.isPresent()) customer.setAddress(existingAddress.get());
            else addressRepository.save(address);

            return customerRepository.save(customer);
        }
        
        catch (DataIntegrityViolationException e) {
            throw new CustomException(
                env.getProperty("database.data-integrity-violation")+": "+e.getMessage(), 
                HttpStatus.CONFLICT);
        }
        catch (DataAccessException e) {
            throw new CustomException(
                env.getProperty("data.access-error")+": "+e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
         catch (Exception e) {
            throw new CustomException(
                env.getProperty("unhadled-error")+": "+e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @Override
    public List<Customer> getAllCustomers() throws CustomException {
        
        try {
            List<Customer> customerList = customerRepository.findAll();
            return customerList;
        } 
        catch (DataAccessException e) {
            throw new CustomException(
                env.getProperty("data.access-error")+": "+e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e) {
            throw new CustomException(
                env.getProperty("unhandled-exception")+": "+e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Customer getUserById(Integer id) throws CustomException {
        
        Optional <Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.get();
    }
}
