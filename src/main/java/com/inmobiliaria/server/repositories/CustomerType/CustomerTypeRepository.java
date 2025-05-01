package com.inmobiliaria.server.repositories.CustomerType;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.inmobiliaria.server.models.CustomerType;

public interface CustomerTypeRepository extends JpaRepository <CustomerType, Integer>{
    
    List<CustomerType> findAll();
    Optional<CustomerType> findById(int id);
    CustomerType save(CustomerType user);
}
