package com.inmobiliaria.server.services.Country;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;

import com.inmobiliaria.server.dto.Country.CountryResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.mappers.CountryMapper;
import com.inmobiliaria.server.models.Country;
import com.inmobiliaria.server.repositories.Country.CountryRepository;

import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService{

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private Environment env;
    @Autowired
    private CountryMapper countryMapper;

    @Override
    public List<CountryResponse> getAllCountries() throws CustomException {
        
        try{
            
            List<Country> country = countryRepository.findAll();

            System.out.println("MY COUNTRIES: "+country);

            List<CountryResponse> countryDtos = countryMapper.toDtoList(country);

            return countryDtos;
        }
        catch (DataAccessException e) {
            
            throw new CustomException(
                env.getProperty("data.access-error")+": "+e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        catch(Exception e){
                throw new CustomException(
                env.getProperty("unhandled-exception")+": "+e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }        
    }
}
