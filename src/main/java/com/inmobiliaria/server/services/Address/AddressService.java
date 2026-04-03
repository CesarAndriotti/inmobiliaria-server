package com.inmobiliaria.server.services.Address;

import java.util.List;
import java.util.Optional;

import com.inmobiliaria.server.dto.Address.AddressResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.Address;

public interface AddressService {

    public Optional<Address> getAddressByStreetnameAndNumber(String streetName, String number) throws CustomException;

    List<AddressResponse> getAllAddresses() throws CustomException;

}
