package com.inmobiliaria.server.repositories.Country;

import com.inmobiliaria.server.models.Country;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Integer>{

    List <Country> findAll();
    Optional<Country> findById(Integer id);
    
    @Query("select p from Country p where p.id=?1")
    Optional<Country> findAll(Integer id);

    @Query("select p from Country p where p.name=?1")
    Optional<Country> findOneName(String name);

    @Query("select p from Country p where p.name like %?1%")
    Optional<Country> findOneLikeName(String name);

    Optional<Country> findByName(String name);

    Optional<Country> findByNameContaining(String name);
}
