package com.inmobiliaria.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.dto.City.CityResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.services.City.CityServiceImpl;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    CityServiceImpl cityServiceImpl;
    @Autowired
    Environment env;

    @GetMapping("/show-list")
    public ResponseEntity<List<CityResponse>> getCityList() throws CustomException{

        List<CityResponse> cityList = cityServiceImpl.getAllCities();

        return ResponseEntity.status(HttpStatus.OK).body(cityList);
    }
}
