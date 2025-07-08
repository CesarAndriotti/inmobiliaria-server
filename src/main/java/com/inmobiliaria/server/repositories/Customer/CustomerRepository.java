package com.inmobiliaria.server.repositories.Customer;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inmobiliaria.server.models.Customer;

@Repository
public interface CustomerRepository extends CrudRepository <Customer, Integer>{

    List<Customer> findAll();
    List<Customer> findByName(String name);
    List<Customer> findByLastname(String lastname);
    List<Customer> findByDateOfBirth(Date dateOfBirth);

    Optional<Customer> findById(Integer id);
    Optional<Customer> findByPhoneNumber(String phoneNumber);

    Optional<Customer> findByIdentificationNumber(String identificationNumber);
    Optional<Customer> findByEmail(String email);
    @Override
    <S extends Customer> S save(S entity);
    Optional<Customer> findByIdentificationNumberOrPhoneNumberOrEmail(String identificationNumber, String phoneNumber, String email);
}