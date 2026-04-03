package com.inmobiliaria.server.repositories.City;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inmobiliaria.server.models.City;
import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer>{

    Optional<City> findByName(String name);
    List<City> findByNameContaining(String name);
}
