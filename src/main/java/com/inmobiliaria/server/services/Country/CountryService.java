package com.inmobiliaria.server.services.Country;

import com.inmobiliaria.server.models.Country;
import java.util.List;
import java.util.Optional;

public interface CountryService{

    List<Country> getAllCountries();
    Optional<Country> saveCountry(Country country);
    Optional<Country> getCountryById(Integer id);
    void deleteCountry(Integer id);
}
