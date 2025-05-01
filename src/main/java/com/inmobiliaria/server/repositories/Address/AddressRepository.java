package com.inmobiliaria.server.repositories.Address;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.inmobiliaria.server.models.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>  {

    List<Address> findAll();
    Optional<Address> findById(int id);
    @Query("SELECT a from Address a where a.streetName =?1 and a.number =?2")
    Optional <Address> findByStreetnameAndNumber(String streetName, String number);
    Address save(Address address);
}
