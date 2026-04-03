package com.inmobiliaria.server.services.Country;

import com.inmobiliaria.server.dto.Country.CountryResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import java.util.List;

public interface CountryService{

    List<CountryResponse> getAllCountries() throws CustomException;
}
