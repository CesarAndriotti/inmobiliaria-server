package com.inmobiliaria.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.inmobiliaria.server.dto.ResponseDto;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.Country;
import com.inmobiliaria.server.repositories.Country.CountryRepository;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    Environment env;

    @GetMapping("/show-list")
    public ResponseEntity<ResponseDto> getCityList() throws CustomException{

        List<Country> countryList = countryRepository.findAll();

        if (countryList == null) {
            throw new CustomException(
                env.getProperty("http.server.internal-server"), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
                countryList,
                env.getProperty("http.success.ok"),
                HttpStatus.OK.value()
            ));
        }
    }
}
