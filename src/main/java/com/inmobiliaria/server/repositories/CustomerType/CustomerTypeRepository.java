package com.inmobiliaria.server.repositories.CustomerType;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.inmobiliaria.server.models.CustomerType;

public interface CustomerTypeRepository extends JpaRepository<CustomerType, Integer>{

    public List<CustomerType> findAll();
    public <S extends CustomerType> S save(S customerType);
    public Optional<CustomerType> findById(Integer id);
    public void deleteById(Integer id); 
}
