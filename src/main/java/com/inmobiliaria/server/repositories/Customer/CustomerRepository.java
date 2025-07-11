package com.inmobiliaria.server.repositories.Customer;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.server.models.Customer;

@Repository
public interface CustomerRepository extends CrudRepository <Customer, Integer>{

    List<Customer> findAll();
    <S extends Customer> S save(S entity);
    Optional<Customer> findByIdentificationNumberOrPhoneNumberOrEmail(String identificationNumber, String phoneNumber, String email);
}