package com.inmobiliaria.server.services.City;

import java.util.List;

import com.inmobiliaria.server.dto.City.CityResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.City;

public interface CityService {

    List<CityResponse> getAllCities() throws CustomException;
}
