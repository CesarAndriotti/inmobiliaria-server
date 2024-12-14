package com.inmobiliaria.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inmobiliaria.server.models.Address;
import com.inmobiliaria.server.models.City;
import com.inmobiliaria.server.repositories.Address.AddressRepository;
import com.inmobiliaria.server.repositories.City.CityRepository;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AddressRepository addressRepository;

    @PostMapping("/register-address")
    public Address registerAddress(@RequestBody Map<String, Object> payload) {

        String streetName = (String) payload.get("streetName");
        String number = (String) payload.get("number");
        Integer cityId = (Integer) payload.get("cityId");

        Optional<City> city = cityRepository.findById(cityId);

        Address address = new Address();
        address.setStreetName(streetName);
        address.setNumber(number);
        address.setCity(city.get());

        return addressRepository.save(address);
    }
}
