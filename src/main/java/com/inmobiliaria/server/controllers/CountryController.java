package com.inmobiliaria.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.inmobiliaria.server.dto.Country.CountryResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.services.Country.CountryServiceImpl;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryServiceImpl countryServiceImpl;

    @GetMapping("/show-list")
    public ResponseEntity<List<CountryResponse>> getCountriesList() throws CustomException{

        List<CountryResponse> countryListDto = countryServiceImpl.getAllCountries();
        return ResponseEntity.status(HttpStatus.OK).body(countryListDto);
    }
}
