package com.inmobiliaria.server.services.Country;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import com.inmobiliaria.server.models.Country;
import com.inmobiliaria.server.repositories.Country.CountryRepository;

import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService{

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> getCountryById(Integer id) {
        
        return countryRepository.findById(id);
    }

    @Override
    public void deleteCountry(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCountry'");
    }

    @Override
    public Optional<Country> saveCountry(Country country) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveCountry'");
    }
}
