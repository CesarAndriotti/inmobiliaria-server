package com.inmobiliaria.server.repositories.Customer;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.server.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Integer>{

    Optional<Customer> findByIdentificationNumberOrPhoneNumberOrEmail(String identificationNumber, String phoneNumber, String email);
}