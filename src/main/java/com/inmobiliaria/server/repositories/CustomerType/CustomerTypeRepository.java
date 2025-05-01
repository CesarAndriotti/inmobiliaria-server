package com.inmobiliaria.server.repositories.CustomerType;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inmobiliaria.server.models.CustomerType;

public interface CustomerTypeRepository extends JpaRepository<CustomerType, Integer>{

    List<CustomerType> findAll();
    Optional<CustomerType> findById(Integer id);
    <S extends CustomerType> S save(S customerType);
    void deleteById(Integer id); 
}
