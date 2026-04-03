package com.inmobiliaria.server.services.Customer;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.inmobiliaria.server.dto.Address.AddressResponse;
import com.inmobiliaria.server.dto.Customer.CustomerRequest;
import com.inmobiliaria.server.dto.Customer.CustomerResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.mappers.AddressMapper;
import com.inmobiliaria.server.mappers.CustomerMapper;
import com.inmobiliaria.server.models.Address;
import com.inmobiliaria.server.models.City;
import com.inmobiliaria.server.models.Customer;
import com.inmobiliaria.server.models.CustomerType;
import com.inmobiliaria.server.repositories.Address.AddressRepository;
import com.inmobiliaria.server.repositories.Customer.CustomerRepository;
import com.inmobiliaria.server.services.Address.AddressServiceImpl;
import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AddressServiceImpl addressServiceImpl;
    @Autowired
    Environment env;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    AddressRepository addressRepository;

    
    @Override
    @Transactional
    public CustomerResponse registerCustomer(CustomerRequest customerRequest) throws CustomException {

        
        try {
         
            //Consulto si ese agente existe, ya que es el actor principal
            Optional<Customer> customerDatabase = customerRepository.
            findByIdentificationNumberOrPhoneNumberOrEmail(
            customerRequest.getIdentificationNumber(),
            customerRequest.getEmail(),
            customerRequest.getPhoneNumber());

            //Si esta presente, lanzo una excepcion
            if(customerDatabase.isPresent()) {
                throw new CustomException(
                        env.getProperty("database.existing-data") + " Data: " +
                customerRequest.getIdentificationNumber() + ", " +
                customerRequest.getEmail() + ", " +
                customerRequest.getPhoneNumber(),
                HttpStatus.CONFLICT);
            }
          
          
          Optional<Address> addressDatabase =
          addressServiceImpl.getAddressByStreetnameAndNumber(customerRequest.getAddressStreetName(), customerRequest.getAddressNumber());
          
          Address address = new Address();
          Customer customer = new Customer();
          customer.setName(customerRequest.getName());
          customer.setLastname(customerRequest.getLastname());
          customer.setDateOfBirth(customerRequest.getDateOfBirth());
          customer.setIdentificationNumber(customerRequest.getIdentificationNumber());
          customer.setEmail(customerRequest.getEmail());
          customer.setPhoneNumber(customerRequest.getPhoneNumber());
          
          CustomerType customerType = new CustomerType();
          customerType.setId(customerRequest.getCustomerTypeId());
          customer.setCustomerType(customerType);
          
          if (addressDatabase.isPresent()) address = addressDatabase.get();
          else {
            address.setStreetName(customerRequest.getAddressStreetName());
            address.setNumber(customerRequest.getAddressNumber());
            City city = new City();
            city.setId(customerRequest.getCityId());
            address.setCity(city);
            addressRepository.save(address);
          }
          
          customer.setAddress(address);
          
          customerRepository.save(customer);
          
          CustomerResponse customerResponse = customerMapper.toDto(customer);
          
          return customerResponse;
          
          } catch (DataIntegrityViolationException e) {
          throw new CustomException(
          env.getProperty("database.data-integrity-violation") + ": " + e.getMessage(),
          HttpStatus.CONFLICT);
          } catch (DataAccessException e) {
          throw new CustomException(
          env.getProperty("data.access-error") + ": " + e.getMessage(),
          HttpStatus.INTERNAL_SERVER_ERROR);
          } catch (Exception e) {
          throw new CustomException(
          env.getProperty("unhadled-error") + ": " + e.getMessage(),
          HttpStatus.INTERNAL_SERVER_ERROR);
          }
         
        
    }

    /*@Override
    @Transactional
    public CustomerResponse updateCustomer(Integer id, CustomerRequest customerRequest) throws CustomException {

        try {
            Optional<Customer> customerDatabase = customerRepository.findById(id);

            if (customerDatabase.isEmpty()) {
                throw new CustomException(
                        env.getProperty("database.entity-not-found"),
                        HttpStatus.NOT_FOUND);
            }

            Customer existingCustomer = customerDatabase.get();

            boolean isSameCustomer = customerRequest.equals(existingCustomer);
            boolean isSameAddress = customerRequest.getAddressId().equals(existingCustomer.getAddress().getId());

            if (isSameCustomer || isSameAddress) {

                throw new CustomException(
                        env.getProperty("database.identical-data"),
                        HttpStatus.CONFLICT);
            }

            Address address = new Address();
            address.setId(customerRequest.getAddressId());
            address.setStreetName(customerRequest.getAddressStreetName());
            address.setNumber(customerRequest.getAddressNumber());
            City city = new City();
            city.setId(customerRequest.getCityId());
            address.setCity(city);

            addressServiceImpl.saveAddress(address);

            Customer customer = customerMapper.toEntity(customerRequest);

            Customer customerUpdated = customerRepository.save(customer);
            CustomerResponse customerResponseUpdated = customerMapper.toDto(customerUpdated);
            return customerResponseUpdated;
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(
                    env.getProperty("database.data-integrity-violation") + ": " + e.getMessage(),
                    HttpStatus.CONFLICT);
        } catch (DataAccessException e) {
            throw new CustomException(
                    env.getProperty("data.access-error") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new CustomException(
                    env.getProperty("unhadled-error") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @Override
    public List<CustomerResponse> getAllCustomers() throws CustomException {

        try {
            
            List<Customer> customerList = customerRepository.findAll();

            return customerMapper.toDtoList(customerList);

        } catch (DataAccessException e) {
            throw new CustomException(
                    env.getProperty("data.access-error") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new CustomException(
                    env.getProperty("unhandled-exception") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
