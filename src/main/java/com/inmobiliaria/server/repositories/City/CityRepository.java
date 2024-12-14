package com.inmobiliaria.server.repositories.City;

import org.springframework.data.repository.CrudRepository;
import com.inmobiliaria.server.models.City;
import java.util.List;
import java.util.Optional;

public interface CityRepository extends CrudRepository<City, Integer>{

    List<City> findAll();
    Optional<City> findById(Integer id);
}
