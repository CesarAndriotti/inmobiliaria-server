package com.inmobiliaria.server.repositories.Address;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.inmobiliaria.server.models.Address;

public interface AddressRepository extends CrudRepository<Address, Integer>  {

    List<Address> findAll();
    Optional<Address> findById(int id);
    Address save(Address address);
}
