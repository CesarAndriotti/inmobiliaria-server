package com.inmobiliaria.server.services.Property;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.dto.Property.PropertyRequest;
import com.inmobiliaria.server.dto.Property.PropertyResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.mappers.PropertyMapper;
import com.inmobiliaria.server.models.Address;
import com.inmobiliaria.server.models.Agent;
import com.inmobiliaria.server.models.City;
import com.inmobiliaria.server.models.Property;
import com.inmobiliaria.server.models.PropertyState;
import com.inmobiliaria.server.models.PropertyType;
import com.inmobiliaria.server.repositories.Address.AddressRepository;
import com.inmobiliaria.server.repositories.Repository.PropertyRepository;
import com.inmobiliaria.server.services.Address.AddressServiceImpl;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    Environment env;
    @Autowired
    PropertyRepository propertyRepository;
    @Autowired
    PropertyMapper propertyMapper;
    @Autowired
    AddressServiceImpl addressServiceImpl;
    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<PropertyResponse> getAllProperties() throws CustomException {

        try {

            List<PropertyResponse> propertyList = propertyMapper.toDtoList(propertyRepository.findAll());
            return propertyList;

        } catch (DataAccessException e) {
            throw new CustomException(
                    env.getProperty("data.access-error") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new CustomException(
                    env.getProperty("unhandled-exception") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public PropertyResponse registerProperty(PropertyRequest propertyRequest) throws CustomException {

        try {

            Property property = new Property();
            property.setTotal_area(propertyRequest.getTotalArea());
            property.setWater(propertyRequest.getWater());
            property.setElectricity(propertyRequest.getElectricity());
            property.setGas(propertyRequest.getGas());
            property.setSewer(propertyRequest.getSewer());
            property.setAsphalt(propertyRequest.getAsphalt());
            property.setSummary(propertyRequest.getSummary());

            PropertyState propertyState = new PropertyState();
            propertyState.setId(propertyRequest.getPropertyStateId());

            PropertyType propertyType = new PropertyType();
            propertyType.setId(propertyRequest.getPropertyTypeId());

            Agent agent = new Agent();
            agent.setId(propertyRequest.getAgentId());

            Address address = new Address();
            address.setStreetName(propertyRequest.getAddressStreetName());
            address.setNumber(propertyRequest.getAddressNumber());

            Optional<Address> addressDatabase = addressServiceImpl.getAddressByStreetnameAndNumber(
                    propertyRequest.getAddressStreetName(), propertyRequest.getAddressNumber());

            if (addressDatabase.isPresent())
                address = addressDatabase.get();
            else {
                address.setStreetName(propertyRequest.getAddressStreetName());
                address.setNumber(propertyRequest.getAddressNumber());
                City city = new City();
                city.setId(propertyRequest.getCityId());
                address.setCity(city);
                addressRepository.save(address);
            }

            property.setPropertyState(propertyState);
            property.setPropertyType(propertyType);
            property.setAgent(agent);
            property.setAddress(address);

            Property propertySaved = propertyRepository.save(property);
            PropertyResponse propertyResponse = propertyMapper.toDto(propertySaved);
            return propertyResponse;

        } catch (DataAccessException e) {
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
