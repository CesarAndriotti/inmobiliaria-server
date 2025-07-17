package com.inmobiliaria.server.services.Customer;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.Address;
import com.inmobiliaria.server.models.Customer;
import com.inmobiliaria.server.repositories.Customer.CustomerRepository;
import com.inmobiliaria.server.services.Address.AddressServiceImpl;
import com.inmobiliaria.server.services.Owner.OwnerServiceImpl;
import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AddressServiceImpl addressServiceImpl;
    @Autowired
    Environment env;
    @Autowired
    OwnerServiceImpl ownerServiceImpl;

    @Override
    @Transactional
    public Customer registerCustomer(Customer customer) throws CustomException {

        try {

            Optional<Customer> customerDatabase = customerRepository.findByIdentificationNumberOrPhoneNumberOrEmail(
                customer.getIdentificationNumber(), 
                customer.getPhoneNumber(), 
                customer.getEmail());

            if (customerDatabase.isPresent()) {
                throw new CustomException(
                    env.getProperty("data.identical-data"), 
                    HttpStatus.CONFLICT
                );
            }

            Address address = customer.getAddress();
            Optional <Address> addressDatabase = addressServiceImpl.getAddressByStreetnameAndNumber(address);
        
            if (addressDatabase.isPresent()) customer.setAddress(addressDatabase.get());
            else addressServiceImpl.saveAddress(address);

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
    @Transactional
    public Customer updateCustomer(Customer customer) throws CustomException {
        
        try {
            Optional<Customer> customerDatabase = customerRepository.findById(customer.getId());

            if (customerDatabase.isEmpty()) {
                throw new CustomException(
                    env.getProperty("database.entity-not-found"),
                    HttpStatus.NOT_FOUND
                );
            }

            Customer existingCustomer = customerDatabase.get();

            boolean isSameCustomer = customer.equals(existingCustomer);
            boolean isSameAddress = customer.getAddress().equals(existingCustomer.getAddress());

            if (isSameCustomer || isSameAddress) {
                
                throw new CustomException(
                    env.getProperty("database.identical-data"),
                    HttpStatus.CONFLICT
                );
            }

            addressServiceImpl.saveAddress(customer.getAddress());
            Customer customerUpdated = customerRepository.save(customer);

            return customerUpdated;
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
}
