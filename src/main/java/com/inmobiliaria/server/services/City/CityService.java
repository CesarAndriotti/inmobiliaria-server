package com.inmobiliaria.server.services.City;

import java.util.List;
import java.util.Optional;

import com.inmobiliaria.server.models.City;

public interface CityService {

    List<City> getAllCities();
    Optional<City> findById();
}
