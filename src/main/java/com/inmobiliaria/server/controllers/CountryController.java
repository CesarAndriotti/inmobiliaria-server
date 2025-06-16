package com.inmobiliaria.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import com.inmobiliaria.server.models.Country;
import com.inmobiliaria.server.services.Country.CountryServiceImpl;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryServiceImpl countryService;

    @GetMapping("/show-list")
    public List<Country> getCountriesList() {
        return countryService.getAllCountries();
    }
}
