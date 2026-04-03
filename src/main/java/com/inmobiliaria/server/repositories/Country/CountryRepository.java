package com.inmobiliaria.server.repositories.Country;

import com.inmobiliaria.server.models.Country;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer>{

    Optional<Country> findByName(String name);
    List<Country> findByNameContaining(String name);
}
