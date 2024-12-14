package com.inmobiliaria.server.repositories.Customer;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.inmobiliaria.server.models.Customer;

public interface CustomerRepository extends CrudRepository <Customer, Integer>{

    List<Customer> findAll();
    List<Customer> findByName(String name);
    List<Customer> findByLastname(String lastname);
    List<Customer> findByDateOfBirth(Date dateOfBirth);

    Optional<Customer> findById(int id);
    Optional<Customer> findByPhoneNumber(String phoneNumber);

    Optional<Customer> findByIdentificationNumber(String identificationNumber);
    Optional<Customer> findByEmail(String email);
    Customer save(Customer Customer);
}