package com.inmobiliaria.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.models.City;
import com.inmobiliaria.server.services.City.CityServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {

    @Autowired
    CityServiceImpl cityService;

    @GetMapping("/cities-list")
    public List<City> citiesList(){
  
        return cityService.getAllCities();
    }
}
