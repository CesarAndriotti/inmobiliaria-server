package com.inmobiliaria.server.services.Address;

import java.util.Optional;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.Address;

public interface AddressService {

    public Optional<Address> getAddressByStreetnameAndNumber(Address address) throws CustomException;
    public Address saveAddress(Address address) throws CustomException;
}
