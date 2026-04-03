package com.inmobiliaria.server.services.City;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.inmobiliaria.server.dto.City.CityResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.mappers.CityMapper;
import com.inmobiliaria.server.models.City;
import com.inmobiliaria.server.repositories.City.CityRepository;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    CityRepository cityRepository;
    @Autowired
    Environment env;
    @Autowired
    CityMapper cityMapper;

    @Override
    public List<CityResponse> getAllCities() throws CustomException {
        
        try {
            List<CityResponse> cityResponseList = cityMapper.toDtoList(cityRepository.findAll());
            return cityResponseList;
        }  catch (DataAccessException e) {

            throw new CustomException(
                    env.getProperty("data.access-error") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new CustomException(
                    env.getProperty("unhandled-exception") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
