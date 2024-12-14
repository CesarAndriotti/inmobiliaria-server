package com.inmobiliaria.server.services.City;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inmobiliaria.server.models.City;
import com.inmobiliaria.server.repositories.City.CityRepository;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    CityRepository cityRepository;

    @Override
    public List<City> getAllCities() {
        
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> findById() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}
